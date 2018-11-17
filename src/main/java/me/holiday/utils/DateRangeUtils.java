package me.holiday.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 */
public class DateRangeUtils {
	
	public static List<LocalDate> getDateRangeByDailyInterval(LocalDate startAt, LocalDate endAt) {
		return LongStream.iterate(0, i -> i + 1)
				.limit(ChronoUnit.DAYS.between(startAt, endAt.plusDays(1)))
				.mapToObj(startAt::plusDays)
				.collect(Collectors.toList());
	}
	
	public static List<LocalDate> getDateRangeByMonthInterval(LocalDate startAt, LocalDate endAt) {
		return LongStream.iterate(0, i -> i + 1)
				.limit(ChronoUnit.MONTHS.between(startAt, endAt.plusMonths(1)))
				.mapToObj(startAt::plusMonths)
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		System.out.println(DateRangeUtils.getDateRangeByDailyInterval(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31))
											.stream()
											.map(LocalDate::toString)
											.collect(Collectors.joining("\n")));
		
		System.out.println();
		
		System.out.println(DateRangeUtils.getDateRangeByMonthInterval(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31))
											.stream()
											.map(LocalDate::toString)
											.collect(Collectors.joining("\n")));
	}
}
