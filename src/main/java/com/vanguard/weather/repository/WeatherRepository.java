package com.vanguard.weather.repository;

import com.vanguard.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
