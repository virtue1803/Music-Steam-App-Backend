package vn.edu.iuh.fit.music_steam_app_backend.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Cấu hình CORS cho phép mọi nguồn hoặc chỉ cho phép từ Snack Expo
        registry.addMapping("/api/**")
                .allowedOrigins("https://snack-web-player.s3.us-west-1.amazonaws.com") // Địa chỉ của Snack Expo
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type")
                .allowCredentials(true);
    }
}
