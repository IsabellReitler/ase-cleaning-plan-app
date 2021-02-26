package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<BODY_CLASS> implements HttpResponse.BodyHandler<BODY_CLASS> {

    private final Class<BODY_CLASS> bodyClass;

    public JsonBodyHandler(Class<BODY_CLASS> bodyClass) {

        this.bodyClass = bodyClass;
    }

    @Override
    public HttpResponse.BodySubscriber<BODY_CLASS> apply(HttpResponse.ResponseInfo responseInfo) {

        return asJSON(bodyClass);
    }


    public static <T> HttpResponse.BodySubscriber<T> asJSON(Class<T> targetType) {

        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);

        return HttpResponse.BodySubscribers.mapping(upstream,
                (String body) -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Do not map JSON content for which no mapping exists in DTO
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                        return objectMapper.readValue(body, targetType);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
    }
}
