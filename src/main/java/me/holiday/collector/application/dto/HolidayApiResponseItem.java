package me.holiday.collector.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-18
 */
@Getter
@Setter
@NoArgsConstructor
public class HolidayApiResponseItem {
	private String dateKind;
	private String dateName;
	private String locdate;
	private String isHoliday;
	private Integer seq;
	
	public LocalDate getLocalDate() {
		return LocalDate.parse(this.locdate, DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
	
	public Boolean isHoliday() {
		return "Y".equalsIgnoreCase(isHoliday);
	}
	
	public static void main(String[] args) {
		System.out.println(LocalDate.parse("20181003", DateTimeFormatter.ofPattern("yyyyMMdd")));
	}
}
