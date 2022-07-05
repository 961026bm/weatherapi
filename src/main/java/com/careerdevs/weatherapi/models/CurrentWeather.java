package com.careerdevs.weatherapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

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

    public static class Coord {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"lon\":").append(lon);
            sb.append(", \"lat\":").append(lat);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Main {
        private float temp;
       // @JsonProperty("feels_like")
        private float feelsLike;
        private float temp_min;
        private float temp_max;
        private int pressure;
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

        public int getPressure() {
            return pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"temp\":").append(temp);
            sb.append(", \"feelsLike\":").append(feelsLike);
            sb.append(", \"temp_min\":").append(temp_min);
            sb.append(", \"temp_max\":").append(temp_max);
            sb.append(", \"pressure\":").append(pressure);
            sb.append(", \"humidity\":").append(humidity);
            sb.append('}');
            return sb.toString();
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

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"id\":").append(id);
            sb.append(", \"main\":\"").append(main).append('"');
            sb.append(", \"description\":\"").append(description).append('"');
            sb.append(", \"icon\":\"").append(icon).append('"');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"").append(name).append('"');
        sb.append(", \"timezone\":").append(timezone);
        sb.append(", \"visibility\":").append(visibility);
        sb.append(", \"coord\":").append(coord);
        sb.append(", \"main\":").append(main);
        sb.append(", \"weather\":").append(Arrays.toString(weather));
        sb.append('}');
        return sb.toString();
    }

    public CurrentWeatherReport createReport(String units) {
        return new CurrentWeatherReport(getName(),
                getCoord(),
                getMain(),
                getWeather()[0],
                units);
    }
}
