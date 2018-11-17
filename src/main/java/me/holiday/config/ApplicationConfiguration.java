package me.holiday.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project collector
 * @since 2018-10-17
 * 
 * Application configuration
 */
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "collecting-api")
@Getter
@Setter
public class ApplicationConfiguration {
	/**
	 * API 호출시 response의 media type
	 */
	private String contentType;
	
	/**
	 * API Key
	 */
	private String key;
}
