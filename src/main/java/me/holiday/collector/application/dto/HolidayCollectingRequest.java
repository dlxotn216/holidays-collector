package me.holiday.collector.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 * <p>
 * 공휴일 데이터 조회 요청을 위한 Request DTO
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
public class HolidayCollectingRequest {
	private final LocalDate defaultStartAt = LocalDate.of(1000, 1, 1);
	private final LocalDate defaultEndAt = LocalDate.of(9999, 12, 31);
	
	/**
	 * Service URL
	 */
	private String url;
	
	/**
	 * Service URL 호출을 위한 API Key
	 */
	private String apiKey;
	
	/**
	 * Service 호출시 응답 타입
	 */
	private MediaType contentType;
	
	/**
	 * 국가 코드로 요청에 담긴 국가코드가
	 * Result query에 Nation_CODE 값으로 들어간다
	 * <p>
	 * 추후 한국의 공휴일 외 다른 국가에 대한 공휴일 수집이 필요할 경우
	 * 아래 field를 기반으로 다른 Service를 주입하는 형태로 확장 필요
	 */
	private String nationCode;
	
	/**
	 * 시작일로 null인 경우 <code>defaultStartAt</code>을 기본값으로 가진다
	 */
	private String startAt;
	
	/**
	 * 종료일로 null인 경우 <code>defaultEndAt</code>을 기본값으로 가진다
	 */
	private String endAt;
	
	public HolidayCollectingRequest(String url, String apiKey, MediaType contentType, String nationCode, String startAt, String endAt) {
		this.url = url;
		this.apiKey = apiKey;
		this.contentType = contentType;
		this.nationCode = nationCode;
		this.startAt = startAt;
		this.endAt = endAt;
	}
	
	public Period getPeriod() {
		return Period.between(getStartAt(), getEndAt());
	}
	
	public LocalDate getStartAt() {
		return startAt == null ? defaultStartAt : LocalDate.parse(this.startAt, DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	public LocalDate getEndAt() {
		return endAt == null ? defaultEndAt : LocalDate.parse(this.endAt, DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
