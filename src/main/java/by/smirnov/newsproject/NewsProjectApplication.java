package by.smirnov.newsproject;

import by.smirnov.newsproject.configuration.ConnectionPoolConfig;
import by.smirnov.newsproject.configuration.OpenAPIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "by.smirnov.newsproject")
@Import({ConnectionPoolConfig.class, OpenAPIConfig.class})
public class NewsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsProjectApplication.class, args);
	}

}
