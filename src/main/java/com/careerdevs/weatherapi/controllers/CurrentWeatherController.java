package com.careerdevs.weatherapi.controllers;

import com.careerdevs.weatherapi.models.CurrentWeather;
import com.careerdevs.weatherapi.models.CurrentWeatherReport;
import com.careerdevs.weatherapi.validation.WeatherValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCity (RestTemplate restTemplate, @PathVariable String cityName ) {

        try {
            String units = "imperial";
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(cityName, units);

            // if validation fails in any way, return error message
            if (validationErrors.size() != 0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units" + units;
            String openWeatherURL = BASE_URL + queryString;

            System.out.println();

            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);

            assert owRes != null;
         //   System.out.println("City: " + openWeatherResponse.getName());
//            System.out.println("Temp: " + openWeatherResponse.getMain().getTemp());
//            System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());

            String openWeatherResponse = restTemplate.getForObject(openWeatherURL, String.class);

            return ResponseEntity.ok(openWeatherResponse);
            //return ResponseEntity.ok("api key: " + apiKey);

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("City Not Found: " + cityName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
//
//    @GetMapping("/city/{cityName}/{units}")
//    public ResponseEntity<?> getCurrentWeatherByCityRP (RestTemplate restTemplate,
//                                                        @PathVariable String cityName,
//                                                        @PathVariable String units) {
//
//        try {
//            String apiKey = env.getProperty("OW_API_KEY");
//            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units" + units;
//            String openWeatherURL = BASE_URL + queryString;
//
//            System.out.println();
//            //String openWeatherResponse = restTemplate.getForObject(openWeatherURL, String.class);
//
////            CurrentWeather openWeatherResponse = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);
//
////          assert openWeatherResponse != null;
////            System.out.println("City: " + openWeatherResponse.getName());
////            System.out.println("Temp: " + openWeatherResponse.getMain().getTemp());
////            System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());
////
////            return ResponseEntity.ok(openWeatherResponse);
//            //return ResponseEntity.ok("api key: " + apiKey);
//
//            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);
//
//            assert owRes != null;
//
//
//           // System.out.println(report);
//            return ResponseEntity.ok(owRes.createReport(units));
//
//        } catch (HttpClientErrorException.NotFound e) {
//            return ResponseEntity.status(404).body("City Not Found: " + cityName);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getClass());
//            return ResponseEntity.internalServerError().body(e.getMessage());
//
//        }
//
//    }

    @GetMapping("/city")
    public ResponseEntity<?> getCurrentWeatherByCityRequestParams (
            RestTemplate restTemplate,
            @RequestParam(value = "name") String cityName,
            @RequestParam(defaultValue = "imperial") String units
    ) {
        try {
            HashMap<String, String> validationErrors = WeatherValidation.validateQuery(cityName, units);
            //ArrayList<String> validationErrors = new ArrayList<>();

            // if validation fails in any way, return error message
            if (validationErrors.size() != 0) {
                return ResponseEntity.badRequest().body(validationErrors);
            }
//            System.out.println(Arrays.toString(validationErrors.toArray()));
//            System.out.println("Name:" + cityName + " - Units:" + units);

            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units" + units;
            String openWeatherURL = BASE_URL + queryString;

            //System.out.println();

            CurrentWeather owRes = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);
            assert owRes != null;

            return ResponseEntity.ok(owRes.createReport(units));

            } catch (HttpClientErrorException.NotFound e) { //cityName validation
                 return ResponseEntity.status(404).body("City Not Found: " + cityName);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getClass());
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
    }
}
    //String units = "imperial";
    //String city = "providence";