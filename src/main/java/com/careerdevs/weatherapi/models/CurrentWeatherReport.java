package com.careerdevs.weatherapi.models;

public class CurrentWeatherReport {

    private String name;
    private String main;
    private String description;
    private String units;
    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private float lon;
    private float lat;

    public CurrentWeatherReport(
            String name,
            CurrentWeather.Main coords,
            CurrentWeather.Main main,
            CurrentWeather.Weather weather,
            String units
    ) {
        this.name = name;
        this.units = units;
        lon = coord.getLon();
        lat = coord.getLat();
        temp = main.getTemp();
        feelsLike = main.getFeelsLike();
        tempMin = main.getTemp_min();
        tempMax = main.getTemp_max();
        pressure = main.getPressure();
        humidity = main.getHumidity();
        this.main = weather.getMain();
        description = weather.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getUnits() {
        return units
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
