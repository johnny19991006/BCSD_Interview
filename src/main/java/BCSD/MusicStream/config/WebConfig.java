package BCSD.MusicStream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/music/musicMP3/**")
                .addResourceLocations("classpath:/static/musicMP3/");
        registry.addResourceHandler("/music/musicIcon/**")
                .addResourceLocations("classpath:/static/musicIcon/");
    }
}
