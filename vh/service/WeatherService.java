package cz.vh.service;

import cz.vh.City;
import cz.vh.connector.WeatherApiConnector;
import cz.vh.dto.WeatherApiDto;
import cz.vh.dto.WeatherDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public WeatherDto getWeatherForCity(City cityEnum){
        WeatherApiConnector connector = new WeatherApiConnector();

        return transformDto(connector.getWeatherForCity(cityEnum));
    }

    private WeatherDto transformDto(WeatherApiDto apiDTO){
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setWeather_description((apiDTO.getCurrent().getCondition().getText()));
        weatherDto.setLocation(apiDTO.getLocation().getName());
        weatherDto.setTimestamp(apiDTO.getCurrent().getLast_updated());
        weatherDto.setTemp_celsius(apiDTO.getCurrent().getTemp_c());
        weatherDto.setWind_direction(apiDTO.getCurrent().getWind_dir());
        weatherDto.setRelative_humidity(apiDTO.getCurrent().getHumidity());
        weatherDto.setWind_speed_m_per_s(apiDTO.getCurrent().getWind_kph()/3.6);

        return weatherDto;
    }
}
