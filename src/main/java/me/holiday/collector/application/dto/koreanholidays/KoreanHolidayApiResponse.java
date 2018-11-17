package me.holiday.collector.application.dto.koreanholidays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 */

/**
 * 공휴일 조회 API의 Response mapping을 위한 DTO
 * 현 시점 기준으로 아래의 형식을 따르며
 * 엄격하게 response의 형식과 dto의 형식이 일치해야 함
 * 
 * {
 * 	"response": {
 * 		"header": {
 * "		resultCode": "00",
 * 		"resultMsg": "NORMAL SERVICE."
 * 	},
 * 	"body": {
 * 		"items": {
 * 			"item": [{
 * 			"dateKind": "01",
 * 			"dateName": "추석",
 * 			"isHoliday": "Y",
 * 			"locdate": 20180923,
 * 			"seq": 1
 * 		},
 * 		{
 * 			"dateKind": "01",
 * 			"dateName": "추석",
 * 			"isHoliday": "Y",
 * 			"locdate": 20180924,
 * 			"seq": 1
 * 			}
 * ....
 * 		}
 * }
 */
@Getter
@Setter
@NoArgsConstructor
public class KoreanHolidayApiResponse {
	private HolidayApiResponseInfo response;
	
	public KoreanHolidayApiResponseItems getItems() {
		return response.getBody().getItems();
	}
}

@Getter
@Setter
@NoArgsConstructor
class HolidayApiResponseInfo {
	private HolidayApiResponseHeader header;
	private HolidayApiResponseBody body;
}

@Getter
@Setter
@NoArgsConstructor
class HolidayApiResponseHeader {
	private String resultCode;
	private String resultMsg;
}

@Getter
@Setter
@NoArgsConstructor
class HolidayApiResponseBody {
	private KoreanHolidayApiResponseItems items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
	
	public KoreanHolidayApiResponseItems getItems() {
		return items == null
				? new KoreanHolidayApiResponseItems()
				: items;
	}
}

