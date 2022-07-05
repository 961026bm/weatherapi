package com.careerdevs.weatherapi.models;

import com.careerdevs.weatherapi.models.Forecast.ForecastWeatherData;

import java.util.ArrayList;

public class ForecastReport {

    private final String cityName;
    private final String country;
    private final int population;
    private final CurrentWeather.Coord coord;
    private final int reportCount;
    //private final ForecastReportEntry[] reports;
    private final ArrayList<ForecastReportEntry> reports;

    public ForecastReport(Forecast forecast, String units) {
        cityName = forecast.getCity().getName();
        country = forecast.getCity().getCountry();
        population =forecast.getCity().getPopulation();
        this.coord = forecast.getCity().getCoord();
        reportCount = forecast.getList().length;
       // reports = new ForecastReportEntry[forecast.getList().length];
        reports = new ArrayList<>();

        for (int i = 0; i < forecast.getList().length; i++) {
           // reports[i] = new ForecastReportEntry(forecast.getList()[i]);
            reports.add(new ForecastReportEntry(forecast.getList()[i], units));
        }

    }

    public static class ForecastReportEntry {
        private String dateTime;
        private String description;
        private String temp;
        private String percentageOfPrecipitation;

        public ForecastReportEntry(ForecastWeatherData wd, String units) {
            description = wd.getWeather()[0].getMain() + " - " + wd.getWeather()[0].getDescription();
            dateTime = wd.getDateTime();
            temp = wd.getMain().getTemp() + "°" + (units.equals("imperial") ? "F" : "C");

            percentageOfPrecipitation = (wd.getPop() * 100) + "%";
        }

        public String getDescription() {
            return description;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPercentageOfPrecipitation() {
            return percentageOfPrecipitation;
        }

    }

    public String getCountry() {
        return country;
    }

    public int getReportCount() {
        return reportCount;
    }

    public String getCityName() {
            return cityName;
        }

        public int getPopulation() {
            return population;
        }

}
