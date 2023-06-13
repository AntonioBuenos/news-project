package by.smirnov.newsproject.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String VERSION = "1.0.1";
    private static final String TITLE = "News project";
    private static final String DESCRIPTION = "This News project Application is a Spring-based java backend " +
            "web app for news posting and commenting.";
    private static final String LICENCE = "Not licenced.";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name(LICENCE);
    }
}
