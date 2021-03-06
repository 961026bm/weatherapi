package com.careerdevs.weatherapi.validation;

import java.util.HashMap;

public class WeatherValidation {

    public static HashMap<String, String> validateQuery(String city, String units) {
        HashMap<String, String> validationErrors = new HashMap<>();
        // validation - name
        // name cant be blank
        if (city.trim().equals("")) {
            validationErrors.put("city", "city name required");

        } else if (
                !city.replaceAll("[a-zA-Z -]", "").equals(city)
        ) {
            // name should not include special chars/numbers
            validationErrors.put("city", "Invalid City Name");
        }

       // validation - units
        // is it metric or imperial
        if (!units.equals("metric") && !units.equals("imperial")) {
            validationErrors.put("units", "units must be metric OR imperial");
        }

        return validationErrors;

    }
}
