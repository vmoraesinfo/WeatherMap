package com.example.synelience.controler;


import com.example.synelience.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/health")
    public String health(){
        return "Server up and running.";
    }

    @GetMapping("/weather")
    public ResponseEntity weather(@RequestParam String city) throws JsonProcessingException {
        return ResponseEntity.ok(weatherService.callWeatherService(city));
    }
}
