package BCSD.MusicStream.repository;

import BCSD.MusicStream.domain.Category;
import BCSD.MusicStream.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findWeatherByName(String name);
}
