package de.reitler.plugin.holydaymode;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holydaymode")
public class HolydayModeController {

    /**
     *
     * @param body: JSON (roommateId, enddate)
     * @return JSON- (roommateId, enddate)
     */
    @PostMapping
    public HttpEntity setHolydayMode(@RequestBody String body){
        //TODO
        return null;
    }

    /**
     *
     * @return List of all holydaymodes currently set, can be null
     */
    @GetMapping
    public HttpEntity getAllHolydayModes(){
        //TODO
        return null;
    }

    /**
     *
     * @param roommateId Id of the Roommate
     * @return  the HolydayModeObject of the given Roommate, can be null
     */
    @GetMapping("/{id}")
    public HttpEntity getHolydayMode(@PathVariable(name="id") String roommateId){
        //TODO
        return null;
    }

    /**
     *
     * @param roommateId
     * @return
     */
    @DeleteMapping("/r{id}")
    public HttpEntity removeHolydayMode(@PathVariable(name="id") String roommateId){
        //TODO
        return null;
    }
}
