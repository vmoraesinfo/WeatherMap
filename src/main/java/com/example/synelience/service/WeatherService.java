package com.example.synelience.service;


import com.example.synelience.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

     public Weather callWeatherService(String city) throws JsonProcessingException {
        if(city == null) return null;
        final String uri = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=f20773ed0134b8a28964ebcf763b73d5";
        ObjectMapper objectMapper = new ObjectMapper();
        String result = restTemplate.getForObject(uri, String.class);
        JsonNode root = objectMapper.readTree(result);
        Weather weather = new Weather();
        weather.setCity(city);
        weather.setUnit("metric");
        weather.setTemperature(root.get("main").get("temp").asDouble());
        weather.setFeelsLike(root.get("main").get("feels_like").asDouble());
        weather.setTempMin(root.get("main").get("temp_min").asDouble());
        weather.setTempMax(root.get("main").get("temp_max").asDouble());
        weather.setPressure(root.get("main").get("pressure").asInt());
        weather.setHumidity(root.get("main").get("humidity").asInt());
        weather.setWindSpeed(root.get("wind").get("speed").asInt());
        return weather;
    }
}
