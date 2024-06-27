package com.vanguard.weather.service;

import com.vanguard.weather.exception.WeatherException;
import com.vanguard.weather.model.Weather;
import com.vanguard.weather.model.WeatherResponse;
import com.vanguard.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    WebClient webClient;

    @Autowired
    WeatherRepository weatherRepository;

    @Value("${weatherservice.apikey.1}")
    private String apiKey1;

    @Value("${weatherservice.apikey.2}")
    private String apiKey2;

    @Value("${weatherservice.apikey.3}")
    private String apiKey3;

    @Value("${weatherservice.apikey.4}")
    private String apiKey4;

    @Value("${weatherservice.apikey.5}")
    private String apiKey5;

    public String getWeatherInformation(String location){
        try {

            List<String> apiKeys = Arrays.asList(apiKey1, apiKey2, apiKey3, apiKey4, apiKey5);

            String apiKey = apiKeys.get(new Random().nextInt(apiKeys.size()));

            WeatherResponse weatherResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("q", location).queryParam("APPID", apiKey).build())

                    .retrieve()
                    .onStatus(HttpStatusCode::isError,
                            error -> Mono.error(new WeatherException()))

                    .bodyToMono(WeatherResponse.class).block();

            if (weatherResponse != null && !weatherResponse.getWeather().isEmpty()) {
                Weather weather = weatherResponse.getWeather().get(0);
                weatherRepository.save(weather);
                return weather.getDescription();
            } else {
                log.error("Failed to retrieve weather information for location: {}", location);
                throw new WeatherException();
            }
        }
        catch (WeatherException e) {
            log.error("ERROR");
            throw new WeatherException();
        }
    }
}
