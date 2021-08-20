package com.example.synelience.model;

import lombok.Data;

@Data
public class Weather {

    private String city;
    private String unit;
    private double temperature;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private int windSpeed;

}
