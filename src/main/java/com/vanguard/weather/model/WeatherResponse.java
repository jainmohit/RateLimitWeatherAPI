package com.vanguard.weather.model;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

}
