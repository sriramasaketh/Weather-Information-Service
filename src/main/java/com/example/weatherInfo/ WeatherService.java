package com.example.weatherinfo.service;

import com.example.weatherinfo.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class WeatherService {

    @Value("${weatherstack.api.key}")
    private String apiKey;

    public Weather getWeather(String city) {
        String url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonObj = new JSONObject(response);
        String description = jsonObj.getJSONObject("current").getJSONArray("weather_descriptions").getString(0);
        double temperature = jsonObj.getJSONObject("current").getDouble("temperature");

        return new Weather(city, description, temperature);
    }
}
