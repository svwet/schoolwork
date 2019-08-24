package ch.teko.svenboban.onlineshop.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sven.wetter@edu.teko.ch
 */


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**")
                .allowedOrigins("**")
                .allowedMethods("GET", "POST")
                .allowCredentials(false).maxAge(3600);
    }
}
