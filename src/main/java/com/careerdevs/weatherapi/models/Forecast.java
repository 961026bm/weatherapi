package com.careerdevs.weatherapi.models;

import com.careerdevs.weatherapi.models.CurrentWeather.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {
    private  City city;
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

        @JsonProperty("dt_txt")
        private String dateTime;

        private float pop;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String name;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Coord coord;

        private String dt_txt;

        public String getDt_txt(){
            return dt_txt;
        }

        public float getPop() {
            return pop;
        }

        public String getDateTime() {
            return dateTime;
        }
    }


    public City getCity() {
        return city;
    }

    public ForecastWeatherData[] getList() {
        return list;
    }
}
