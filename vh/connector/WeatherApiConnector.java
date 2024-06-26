package cz.vh.connector;

import cz.vh.City;
import cz.vh.dto.WeatherApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class WeatherApiConnector {
    //https://api.weatherapi.com/v1/current.json?key=27b4a7b4bb5847b2a0f75112241906&q=Ostrava&aqi=no

    private static final String baseUrl = "https://api.weatherapi.com/";
    private static final String urlParameters = "v1/current.json?key=";
    private static final String apiKey = "27b4a7b4bb5847b2a0f75112241906";
    private  static final String url = baseUrl+urlParameters+apiKey+"&q=";

    public WeatherApiDto getWeatherForCity(City city){
        RestTemplate template = new RestTemplate();
        URI uri = null;
        try {
            uri = new URI(url+city);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<WeatherApiDto> response = template.getForEntity(uri,WeatherApiDto.class);
        return response.getBody();
    }
}
