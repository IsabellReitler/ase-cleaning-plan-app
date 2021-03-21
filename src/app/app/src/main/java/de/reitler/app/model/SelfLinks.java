package de.reitler.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.List;

public class SelfLinks{
    @JsonProperty("self")
    HRef self;
}

class HRef{
    @JsonProperty("href")
    URI href;
}
