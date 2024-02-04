package BCSD.MusicStream.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class WeatherAPI {
    private final String key;
    public WeatherAPI(@Value("${weather-key}") String key) {
        this.key = key;
    }
    public OpenWeather getWeather(String lat, String lon) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.openweathermap.org/data/2.5/weather");
        urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=Koesan");
        urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + key);
        urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
        urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

        RestTemplate restTemplate = new RestTemplate();
        OpenWeather response = restTemplate.getForObject(urlBuilder.toString(), OpenWeather.class);
        return response;
    }
}
