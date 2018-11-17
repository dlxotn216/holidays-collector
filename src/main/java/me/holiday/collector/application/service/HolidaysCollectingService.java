package me.holiday.collector.application.service;

import me.holiday.collector.application.dto.HolidayCollectingRequest;
import me.holiday.collector.domain.Holiday;
import me.holiday.collector.domain.NationCode;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project collector
 * @since 2018-10-17
 * <p>
 * 공휴일 데이터를 수집하는 Service 인터페이스
 */
public interface HolidaysCollectingService {
	/**
	 * 서비스가 수집하는 대상 국가의 코드를 반환한다
	 *
	 * @return Nation code
	 * @see me.holiday.collector.domain.NationCode
	 */
	NationCode getMappedNationCode();
	
	/**
	 * <code>request</code>를 기반으로 호출한 결과를 통해
	 * 생성된 공휴일 데이터들로 구성된 Merge query를 생성하여 반환한다
	 *
	 * @param request HolidayCollectionRequest
	 * @return 바로 실행가능한 Merge Query
	 */
	Mono<String> getCollectedHolidaysMergeQuery(HolidayCollectingRequest request);
	
	/**
	 * <code>request</code>를 기반으로 호출한 결과를 통해
	 * 생성된 공휴일 데이터들로 구성된 Insert query를 생성하여 반환한다
	 *
	 * @param request HolidayCollectionRequest
	 * @return 바로 실행가능한 Insert Query
	 */
	Mono<String> getCollectedHolidaysQuery(HolidayCollectingRequest request);
	
	/**
	 * <code>request</code>를 기반으로 호출한 결과를 List 형태로 collecting하여 반환한다
	 * <p>
	 * Spring webflux application이 구동된 경우 아래의 메소드 구현체 내에서
	 * blocking call을 통해 값을 수집하는경우 예외가 발생한다
	 * <p>
	 * (blocking call은 webflux 환경에서 호출될 수 없다)
	 *
	 * @param request HolidayCollectionRequest* @param request
	 * @return 수집된 holidays
	 */
	List<Holiday> collectHolidays(HolidayCollectingRequest request);
}
