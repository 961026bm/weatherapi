package com.careerdevs.weatherapi.models;

import com.careerdevs.weatherapi.models.Forecast.ForecastWeatherData;

public class ForecastReport {
    private final String cityName;
    private final String country;
    private final int population;
    private final ForecastReportEntry[] reports;

    public ForecastReport(Forecast forecast) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population =forecast.getCity().getPopulation();

        reports = new ForecastReportEntry[forecast.getList().length];

        for (int i = 0; i < forecast.getList().length; i++) {
            reports[i] = new ForecastReportEntry(forecast.getList()[i]);
        }

    }

    public static class ForecastReportEntry {
        private String dateTime;
        private String description;
        private String temp;
        private String percentageOfPrecipitation;

        public ForecastReportEntry(ForecastWeatherData wd) {
            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
            temp = wd.getMain().getTemp() + "F";
        }

        public String getDescription() {
            return description;
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
