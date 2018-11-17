package me.holiday.collector.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 * <p>
 * Holiday domain
 * <p>
 * 내부의 field를 기반으로 result를 위한 query를 생성한다
 */
@Getter
@Setter
@NoArgsConstructor
public class Holiday implements Comparable<Holiday> {
	
	/**
	 * MST_HOLIDAY 테이블에 Merge를 위한 prefix
	 */
	public static final String MERGE_PREFIX =
			"MERGE INTO MST_HOLIDAY MH\n"
					+ "USING (\n";
	
	/**
	 * MST_HOLIDAY 테이블에 Merge를 위한 postifx
	 */
	public static final String MERGE_POSTFIX =
			") TAB\n" +
					"ON (\n" +
					"    MH.HOL_DATE = TAB.HOL_DATE\n" +
					"    AND MH.NATION_CODE = TAB.NATION_CODE\n" +
					")" +
					"WHEN MATCHED THEN\n" +
					"    UPDATE SET\n" +
					"        MH.HOL_NAME = TAB.HOL_NAME\n" +
					"      , MH.INPUT_tIME = TAB.INPUT_TIME\n" +
					"WHEN NOT MATCHED THEN\n" +
					"    INSERT (NATION_CODE, HOL_DATE, HOL_NAME, DESCRIPTION, REASON, DEL_FLAG, INPUT_TIME, USER_KEY)\n" +
					"    VALUES (TAB.NATION_CODE, TAB.HOL_DATE, TAB.HOL_NAME, TAB.DESCRIPTION, TAB.REASON, TAB.DEL_FLAG, TAB.INPUT_TIME, TAB.USER_KEY)";
	
	private String nationCode;
	private String name;
	private LocalDate date;
	private Boolean isHoliday;
	
	public Holiday(String nationCode, String name, LocalDate date, Boolean isHoliday) {
		this.nationCode = nationCode;
		this.name = name;
		this.date = date;
		this.isHoliday = isHoliday;
	}
	
	/**
	 * Holiday 객체에 대한 값으로 구성된 Inline view를 반환한다
	 *
	 * @return Inline view
	 */
	public String getHolidayInlineViewQuery() {
		return "SELECT "
				+ "   '" + this.nationCode + "' 		NATION_CODE "
				+ ",  '" + this.date + "' 	HOL_DATE "
				+ ",  '" + this.name + "' 	HOL_NAME "
				+ ",  'Initial input' 	DESCRIPTION "
				+ ",  'Initial input' 	REASON "
				+ ",  'N' 				DEL_FLAG "
				+ ",  SYSDATE 			INPUT_TIME "
				+ ",  1628 				USER_KEY "
				+ "FROM DUAL \n"
				;
	}
	
	/**
	 * Holiday 객체에 대한 Insert query 생성
	 *
	 * @return Insert Query
	 */
	public String getHolidayQuery() {
		return String.format("INSERT INTO MST_HOLIDAY(NATION_CODE, HOL_DATE, HOL_NAME, DESCRIPTION, REASON, DEL_FLAG, INPUT_TIME, USER_KEY) "
						+ "VALUES('%s', '%s', '%s', '%s', '%s', '%s', %s, %s);",
				this.nationCode, this.date, this.name,
				"Initial input", "Initial input", "N", "SYSDATE", "1628");
	}
	
	@Override
	public String toString() {
		return "Holiday{" +
				"name='" + name + '\'' +
				", date=" + date +
				", isHoliday=" + isHoliday +
				'}';
	}
	
	@Override
	public int compareTo(Holiday o) {
		return this.getDate().compareTo(o.date);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Holiday holiday = (Holiday) o;
		
		if(nationCode != null ? !nationCode.equals(holiday.nationCode) : holiday.nationCode != null) return false;
		return date != null ? date.equals(holiday.date) : holiday.date == null;
	}
	
	@Override
	public int hashCode() {
		int result = nationCode != null ? nationCode.hashCode() : 0;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}
}
