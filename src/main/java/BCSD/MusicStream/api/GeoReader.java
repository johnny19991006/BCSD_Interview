package BCSD.MusicStream.api;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Component
public class GeoReader {
    private final String DATABASE_CITY_PATH = "src/main/resources/GeoLite2-City.mmdb";
    private DatabaseReader reader;
    public GeoReader() throws IOException {
        File dbFile = new File(DATABASE_CITY_PATH);
        reader = new DatabaseReader.Builder(dbFile).build();
    }

    public CityResponse city(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        return reader.city(ipAddress);
    }
}