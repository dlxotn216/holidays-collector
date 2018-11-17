package me.holiday.collector.application.dto.koreanholidays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-18
 */
@Getter
@Setter
@NoArgsConstructor
public class KoreanHolidayApiResponseItems {
	private List<KoreanHolidayApiResponseItem> item;
	
	public List<KoreanHolidayApiResponseItem> getItems() {
		return item == null 
				? Collections.emptyList()
				: item;
	}
}
