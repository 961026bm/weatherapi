package com.careerdevs.weatherapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentWeather {

    private String name;

    private int timezone;

    private int visibility;

    private Coord coord;

    private Main main;

    private Weather[] weather;

    public String getName() {
        return name;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVisibility() {
        return visibility;
    }

    public Coord getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    private static class Coord {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }

    public static class Main {
        private float temp;
        @JsonProperty("feels_like")
        private float feelsLike;
        private float temp_min;
        private float temp_max;
        private short pressure;
        private float humidity;

        public float getTemp() {
            return temp;
        }

        public float getFeelsLike() {
            return feelsLike;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public short getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", feelsLike=" + feelsLike +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }
}
