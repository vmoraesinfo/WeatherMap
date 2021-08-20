package com.example.synelience.service;

import com.example.synelience.model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    @InjectMocks
    private WeatherService weatherService;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    public void testCallWeatherService_whenCityNull_ShouldReturnNull() throws JsonProcessingException {
        assertNull(weatherService.callWeatherService(null));

    }

    @Test
    public void testCallWeatherService_whenCityHasValue_ShouldReturnWeatherObject() throws JsonProcessingException {
        final String uri = "http://api.openweathermap.org/data/2.5/weather?q=lisbon&units=metric&appid=f20773ed0134b8a28964ebcf763b73d5";
        Weather weather = new Weather();
        weather.setUnit("metric");
        weather.setCity("lisbon");
        weather.setWindSpeed(10);
        weather.setHumidity(1);
        weather.setPressure(6);
        weather.setFeelsLike(2.20);
        weather.setTemperature(3.3);
        weather.setTempMax(5.0);
        weather.setTempMin(2.1);
        String resp = "{\"coord\":{\"lon\":-9.1333,\"lat\":38.7167},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"base\":\"stations\",\"main\":{\"temp\":3.3,\"feels_like\":2.20,\"temp_min\":2.1,\"temp_max\":5.0,\"pressure\":6,\"humidity\":1},\"visibility\":10000,\"wind\":{\"speed\":10,\"deg\":360},\"clouds\":{\"all\":20},\"dt\":1629416111,\"sys\":{\"type\":1,\"id\":6901,\"country\":\"PT\",\"sunrise\":1629438908,\"sunset\":1629487501},\"timezone\":3600,\"id\":2267057,\"name\":\"Lisbon\",\"cod\":200}";
        Mockito.when(restTemplate.getForObject(uri, String.class))
          .thenReturn(resp);

        Weather response = weatherService.callWeatherService("lisbon");
        assertEquals(weather, response);

    }
}
