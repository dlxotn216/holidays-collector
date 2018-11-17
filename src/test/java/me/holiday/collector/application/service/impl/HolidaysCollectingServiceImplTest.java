package me.holiday.collector.application.service.impl;

import me.holiday.collector.application.dto.HolidayCollectingRequest;
import me.holiday.collector.application.service.HolidaysCollectingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * Created by Lee Tae Su on 2018-10-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidaysCollectingServiceImplTest {
	
	@Autowired
	private HolidaysCollectingService holidaysCollectingService;
	
	@Test
	public void Collecting_And_Blocking_테스트() {
		LocalDate startAt = LocalDate.of(2018, 1, 1);
		LocalDate endAt = LocalDate.of(2018, 12, 1);
		;
		holidaysCollectingService.collectHolidays(
				HolidayCollectingRequest.builder()
						.url("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo")
						.apiKey("k23%2FdF5h70%2B5lX7JQ5%2BLIX%2BT%2BcaNiCvFk8wkGsa9cAHa0BzZ5X4IXZgROlzBxDdmIl0v%2Br4chZH3BeizRnSNtQ%3D%3D")
						.contentType(MediaType.APPLICATION_JSON)
						.nationCode("KOR")
						.startAt(startAt.toString())
						.endAt(endAt.toString())
						.build())
				.forEach(System.out::println);
		
	}
	
	@Test
	public void Insert_쿼리_테스트() {
		IntStream.rangeClosed(2015, 2018)
				.forEach(year -> {
					LocalDate startAt = LocalDate.of(year, 1, 1);
					LocalDate endAt = LocalDate.of(year, 12, 1);
					String query = holidaysCollectingService.getCollectedHolidaysQuery(
							HolidayCollectingRequest.builder()
									.url("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo")
									.apiKey("k23%2FdF5h70%2B5lX7JQ5%2BLIX%2BT%2BcaNiCvFk8wkGsa9cAHa0BzZ5X4IXZgROlzBxDdmIl0v%2Br4chZH3BeizRnSNtQ%3D%3D")
									.contentType(MediaType.APPLICATION_JSON)
									.nationCode("KOR")
									.startAt(startAt.toString())
									.endAt(endAt.toString())
									.build()
					).block();
					
					System.out.println("==========result[" + year + "]==========");
					System.out.println(query);
					
					//API를 연달아 호출하면 차단되므로 일정시간 지연
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
		
	}
	
	@Test
	public void Merge_쿼리_테스트() {
		IntStream.rangeClosed(2015, 2018)
				.forEach(year -> {
					LocalDate startAt = LocalDate.of(year, 1, 1);
					LocalDate endAt = LocalDate.of(year, 12, 1);
					String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
					String query = holidaysCollectingService.getCollectedHolidaysMergeQuery(HolidayCollectingRequest.builder()
							.url("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo")
							.apiKey("k23%2FdF5h70%2B5lX7JQ5%2BLIX%2BT%2BcaNiCvFk8wkGsa9cAHa0BzZ5X4IXZgROlzBxDdmIl0v%2Br4chZH3BeizRnSNtQ%3D%3D")
							.contentType(MediaType.APPLICATION_JSON)
							.nationCode("KOR")
							.startAt(startAt.toString())
							.endAt(endAt.toString())
							.build()
					).block();

//					System.out.println("==========result[" + year + "]==========");
					System.out.println(query);
					
					//API를 연달아 호출하면 차단되므로 일정시간 지연
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
		
	}
	
}