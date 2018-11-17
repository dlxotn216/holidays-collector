package me.holiday.collector.application.dto;

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
public class HolidayApiResponseItems {
	private List<HolidayApiResponseItem> item;
	
	public List<HolidayApiResponseItem> getItems() {
		return item == null 
				? Collections.emptyList()
				: item;
	}
}
