package com.careerdevs.weatherapi.controllers;

import com.careerdevs.weatherapi.models.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/current")
public class CurrentWeatherController {

    @Autowired
    private Environment env;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCity (RestTemplate restTemplate, @PathVariable String cityName ) {

        try {
            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=imperial";
            String openWeatherURL = BASE_URL + queryString;

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
    @GetMapping("/city/{cityName}")
    public ResponseEntity<?> getCurrentWeatherByCityRP (RestTemplate restTemplate, @PathVariable("city") String cityName ) {

        try {
            String apiKey = env.getProperty("OW_API_KEY");
            String queryString = "?q=" + cityName + "&appid=" + apiKey + "&units=imperial";
            String openWeatherURL = BASE_URL + queryString;
            //String openWeatherResponse = restTemplate.getForObject(openWeatherURL, String.class);

            CurrentWeather openWeatherResponse = restTemplate.getForObject(openWeatherURL, CurrentWeather.class);

            assert openWeatherResponse != null;
            System.out.println("City: " + openWeatherResponse.getName());
            System.out.println("Temp: " + openWeatherResponse.getMain().getTemp());
            System.out.println("Desc: " + openWeatherResponse.getWeather()[0].getDescription());

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
}
