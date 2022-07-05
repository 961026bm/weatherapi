package com.careerdevs.weatherapi.controllers;

import com.careerdevs.weatherapi.models.CurrentWeather;
import com.careerdevs.weatherapi.models.Forecast;
import com.careerdevs.weatherapi.models.ForecastReport;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api/forecast")

public class ForecastController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getForecastByCityPathVar (RestTemplate restTemplate, @PathVariable String city) {
        try {
            String units = "imperial";
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(city, units);

            // if validation fails in any way, return error message
            if (validationErrors.size() != 0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey;
            String url = BASE_URL + queryString;

            Forecast owRes = restTemplate.getForObject(url, Forecast.class);
            // System.out.println(url);

            //generate report
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));

            //ForecastReport report = new ForecastReport(owRes, units);

            //return ResponseEntity.ok(owRes.createReport(units));

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(484).body("City Not Found: " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/city")
    public ResponseEntity<?> getCurrentWeatherByCityRequestParams (
            RestTemplate restTemplate,
            @RequestParam(value = "name") String city,
            @RequestParam(defaultValue = "imperial") String units,
            @RequestParam(defaultValue = "40") String count
    ) {

        try {
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(city, units);
            //ArrayList<String> validationErrors = new ArrayList<>();

            //validate count is a number
            if(count.replaceAll("[^0-9]", "").equals(count)) {
                validationErrors.put("count", "count must a number");
            } else if (Integer.parseInt(count) < 1 || Integer.parseInt(count) > 40) {
                validationErrors.put("count", "count must be between 1 qnd 40");

            }

            // if validation fails in any way, return error message/s
            if (validationErrors.size() != 0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + city + "&units=" + units + "&appid=" + apiKey + "&cnt=" + count;
            String url = BASE_URL + queryString;

            Forecast owRes = restTemplate.getForObject(url, Forecast.class);
            //CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);

           //generate report
            assert owRes != null;
            return ResponseEntity.ok(owRes.createReport(units));

        } catch (HttpClientErrorException.NotFound e) { //cityName validation
            return ResponseEntity.status(404).body("City Not Found: " + city);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
