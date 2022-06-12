package com.careerdevs.weatherapi.models;

import com.careerdevs.weatherapi.models.CurrentWeather.*;

public class Forecast {

    private ForecastWeatherData[] list;

    public static class City {
        private String name;
        private Coord coord;
        private String country;
        private int population;

        public String getName() {
            return name;
        }

        public Coord getCoord() {
            return coord;
        }

        public String getCountry() {
            return country;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static class ForecastWeatherData extends CurrentWeather {

    }

//    public City getCity() {
//        return city;
//    }

    public ForecastWeatherData[] getList() {
        return list;
    }
}
