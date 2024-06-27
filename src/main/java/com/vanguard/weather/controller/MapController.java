package com.vanguard.weather.controller;

import com.vanguard.weather.service.RateLimitCheckService;
import com.vanguard.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/")
public class MapController {

    @Autowired
    private RateLimitCheckService rateLimitCheckService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String location, @RequestParam String key){
        if (rateLimitCheckService.allowRequest(key)) {
            return weatherService.getWeatherInformation(location);
        } else {
            return "Rate limit exceeded. Please Try again later.";
        }
    }

}
