package be.ipeters.puttingittogether.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for the Putting It Together application.
 * Configures CORS, static resources, and view controllers.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Configure CORS settings for REST API endpoints.
     * Allows frontend applications to consume the API.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS for REST API endpoints (v1)
        registry.addMapping("/api/v1/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
                
        // Allow CORS for H2 console
        registry.addMapping("/h2-console/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Configure static resource handling for CSS, JS, images, etc.
     * Ensures proper serving of Bootstrap, custom CSS, and other assets.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Static resources
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(3600);
                
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCachePeriod(3600);
                
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(3600);
                
        // Bootstrap and other node modules
        registry.addResourceHandler("/node_modules/**")
                .addResourceLocations("classpath:/static/node_modules/")
                .setCachePeriod(3600);
    }

    /**
     * Add simple view controllers for common pages.
     * Reduces the need for controller methods that just return view names.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect root to home
        registry.addRedirectViewController("/", "/home");
        
        // Error page mappings
        registry.addViewController("/error").setViewName("errorpages/error");
        registry.addViewController("/403").setViewName("errorpages/error-403");
        registry.addViewController("/404").setViewName("errorpages/error-404");
        registry.addViewController("/500").setViewName("errorpages/error-500");
    }
}