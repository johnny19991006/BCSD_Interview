package BCSD.MusicStream.service;

import BCSD.MusicStream.api.GeoReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Subdivision;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final GeoReader geoReader;
    public CityResponse findCity(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        return geoReader.city(ipAddress);
    }

    public InetAddress getIpAddress() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.isEmpty()) ip = req.getRemoteAddr();
        if (ip == null || ip.isEmpty()) ip = req.getHeader("Proxy-Client-IP");
        if (ip == null || ip.isEmpty()) ip = req.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.isEmpty()) ip = req.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.isEmpty()) ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.isEmpty()) ip = req.getRemoteAddr();
        if (ip == null || ip.isEmpty()) throw new RuntimeException();

        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ipAddress;
    }
}