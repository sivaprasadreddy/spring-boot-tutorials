package com.sivalabs.bookmarker;

import com.sivalabs.bookmarker.config.BookmarkerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties(value = {BookmarkerProperties.class})
@EnableAspectJAutoProxy
@EnableCaching
public class BookmarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkerApplication.class, args);
	}

}
