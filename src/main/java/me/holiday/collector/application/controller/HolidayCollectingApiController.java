package me.holiday.collector.application.controller;

import me.holiday.collector.application.dto.HolidayCollectingRequest;
import me.holiday.collector.application.service.HolidaysCollectingService;
import me.holiday.collector.application.service.HolidaysCollectingServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static me.holiday.collector.domain.Holiday.MERGE_POSTFIX;
import static me.holiday.collector.domain.Holiday.MERGE_PREFIX;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 * 
 * 공휴일 수집 API 호출 endpoint 
 */
@RestController
public class HolidayCollectingApiController {
	
	private HolidaysCollectingServiceFactory factory;
	
	public HolidayCollectingApiController(HolidaysCollectingServiceFactory factory) {
		Assert.notNull(factory, "Necessary dependency is not present.");
		this.factory = factory;
	}
	
	@GetMapping(value = "/holidays")
	public ResponseEntity<Mono<String>> getHolidays(HolidayCollectingRequest request) {
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("text/plain; charset=UTF-8"))
				.body(factory.getHolidaysCollectingService(request.getNationCode()).getCollectedHolidaysMergeQuery(request)
						.flatMap(s -> Mono.just(MERGE_PREFIX + s + MERGE_POSTFIX)))
				;
	}
}
