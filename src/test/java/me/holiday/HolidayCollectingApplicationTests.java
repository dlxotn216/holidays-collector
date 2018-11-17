package me.holiday;

import me.holiday.config.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidayCollectingApplicationTests {
	@Autowired
	private ApplicationConfiguration configuration;

	@Test
	public void contextLoads() {
		assertThat(configuration.getContentType()).isNotBlank();
		System.out.println(configuration.getContentType());
		
		assertThat(configuration.getKey()).isNotBlank();
		System.out.println(configuration.getKey());
	}

}
