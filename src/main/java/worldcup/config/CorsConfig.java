package worldcup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Definiše CORS pravila za sve rute koje počinju sa /api/
                        .allowedOrigins("http://127.0.0.1:5500") // Dozvoljava zahteve sa tvog domena
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Dozvoljene HTTP metode
                        .allowedHeaders("*") // Dozvoljeni header-i
                        .allowCredentials(true); // Ako koristiš autentifikaciju preko kolačića ili HTTP header-a
            }
        };
    }
}
