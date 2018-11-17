package me.holiday.collector.application.service;

import me.holiday.collector.application.service.impl.KoreanHolidaysCollectingServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-11-17
 * 
 * 공휴일 수집 서비스 구현체 Factory
 */
@Service
public class HolidaysCollectingServiceFactory {
	private List<HolidaysCollectingService> holidaysCollectingServices = new ArrayList<>();
	
	public HolidaysCollectingServiceFactory(KoreanHolidaysCollectingServiceImpl koreanHolidaysCollectingService) {
		this.holidaysCollectingServices.add(koreanHolidaysCollectingService);
	}
	
	/**
	 * HolidaysCollectionService 인터페이스의 구현체에 구현된 getMappedNationCode 메소드에 값을 비교하여 
	 * 적절한 구현체를 반환한다
	 * 
	 * @param nationCode Nation code
	 * @return Nation code 별 공휴일 수집 서비스 구현체
	 */
	public HolidaysCollectingService getHolidaysCollectingService(String nationCode) {
		return this.holidaysCollectingServices.stream().filter(holidaysCollectingService -> holidaysCollectingService.getMappedNationCode().getCode().equalsIgnoreCase(nationCode))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("지원하지 않는 nationCode입니다"));
	}
}
