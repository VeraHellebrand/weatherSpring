package cz.vh.controller;

import cz.vh.City;
import cz.vh.connector.WeatherApiConnector;
import cz.vh.dto.WeatherDto;
import cz.vh.service.WeatherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {
    @CrossOrigin
    @RequestMapping("/weather")
    public List<WeatherDto> getWeather(){
        List<WeatherDto> wdtos = new ArrayList<>();
        WeatherService service = new WeatherService();
        for (City city : City.values()) {
//            WeatherDto weatherDto = service.getWeatherForCity(city);
//            wdtos.add(weatherDto);
            wdtos.add(service.getWeatherForCity(city));
        }
        return wdtos;
    }
    @CrossOrigin
    @RequestMapping("/weather/{city}")
    public WeatherDto getWeatherForCity(@PathVariable("city") String city){
        City cityEnum = City.valueOf(city.toUpperCase());
        WeatherService weatherService = new WeatherService();

        return weatherService.getWeatherForCity(cityEnum);
    }
}
