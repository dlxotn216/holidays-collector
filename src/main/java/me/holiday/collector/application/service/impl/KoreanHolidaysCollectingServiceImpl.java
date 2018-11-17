package me.holiday.collector.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.holiday.collector.application.dto.koreanholidays.KoreanHolidayApiResponse;
import me.holiday.collector.application.dto.HolidayCollectingRequest;
import me.holiday.collector.application.service.HolidaysCollectingService;
import me.holiday.collector.domain.Holiday;
import me.holiday.collector.domain.NationCode;
import me.holiday.utils.DateRangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project collector
 * @since 2018-10-17
 */
@Service("KoreanHolidaysCollectingServiceImpl")
@Slf4j
public class KoreanHolidaysCollectingServiceImpl implements HolidaysCollectingService {
	
	private WebClient.Builder builder;
	
	private final DefaultUriBuilderFactory factory;
	
	@Override
	public NationCode getMappedNationCode() {
		return NationCode.KOR;
	}
	
	public KoreanHolidaysCollectingServiceImpl(WebClient.Builder builder) {
		this.builder = builder;
		
		this.factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
	}
	
	@Override
	public Mono<String> getCollectedHolidaysMergeQuery(HolidayCollectingRequest request) {
		return getHolidayFlux(request)
				.sort(Holiday::compareTo)
				.map(Holiday::getHolidayInlineViewQuery)
				.collect(Collectors.joining("UNION ALL\n"))
				;
	}
	
	@Override
	public Mono<String> getCollectedHolidaysQuery(HolidayCollectingRequest request) {
		return getHolidayFlux(request)
				.sort(Holiday::compareTo)
				.map(Holiday::getHolidayQuery)
				.collect(Collectors.joining("\n"));
	}
	
	@Override
	public List<Holiday> collectHolidays(HolidayCollectingRequest request) {
		return getHolidayFlux(request)
				.collectSortedList()
				.block();
	}
	
	private Flux<Holiday> getHolidayFlux(HolidayCollectingRequest request) {
		return sendRequests(request.getUrl(), request.getApiKey(), request.getContentType(), request.getStartAt(), request.getEndAt())
				.flatMap(response -> Flux.fromIterable(response.getItems().getItems()))
//				.filter(KoreanHolidayApiResponseItem::isHoliday)			//제헌절과 같이 특일이나 공휴일이 아닌 경우를 필터링하기 위한 필터 
				.map(holidayApiResponseItem -> new Holiday(request.getNationCode(), holidayApiResponseItem.getDateName(), holidayApiResponseItem.getLocalDate(), holidayApiResponseItem.isHoliday()))
				.distinct();
	}
	
	private Flux<KoreanHolidayApiResponse> sendRequests(String url, String apiKey, MediaType mediaType, LocalDate startAt, LocalDate endAt) {
		return Flux.fromIterable(DateRangeUtils.getDateRangeByMonthInterval(startAt, endAt))
				.flatMap(date -> sendRequest(url, apiKey, mediaType, date))
//				.log()
				;
		
	}
	
	private Flux<KoreanHolidayApiResponse> sendRequest(String url, String apiKey, MediaType mediaType, LocalDate date) {
		return builder
				.uriBuilderFactory(this.factory)
				.baseUrl(url)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.filter(logRequest())
				.filter(logResposneStatus())
				.build()
				.get()
				.uri(uriBuilder -> this.factory.uriString(url)
						.queryParam("ServiceKey", apiKey)
						.queryParam("solYear", date.format(DateTimeFormatter.ofPattern("YYYY")))
						.queryParam("solMonth", date.format(DateTimeFormatter.ofPattern("MM")))
						.build())
				.accept(mediaType)
				.retrieve()
				.bodyToFlux(KoreanHolidayApiResponse.class);
	}
	
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request url was {}", clientRequest.url());
			return Mono.just(clientRequest);
		});
	}
	
	private ExchangeFilterFunction logResposneStatus() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			log.info("Response status is {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}
}
