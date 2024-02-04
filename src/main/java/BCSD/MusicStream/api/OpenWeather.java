package BCSD.MusicStream.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
public class OpenWeather {

    private List<Weather> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private int visibility;
    private long dt;
    private int timezone;
    private long id;
    private String name;
    private int cod;
    @Getter
    @ToString
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }
    @Getter
    public static class Main {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int pressure;
        private float humidity;
        private float sea_level;
        private float grnd_level;
    }
    @Getter
    public static class Wind {
        private float speed;
        private int deg;
        private float gust;
    }
    @Getter
    public static class Clouds {
        private int all;
    }
    @Getter
    public static class Rain {
        @JsonProperty("1h")
        private float rain1h;

        @JsonProperty("3h")
        private float rain3h;
    }

    @Getter
    public static class Snow {
        @JsonProperty("1h")
        private float snow1h;

        @JsonProperty("3h")
        private float snow3h;
    }
    @Getter
    public static class Sys {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}