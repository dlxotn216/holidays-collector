package me.holiday.collector.domain;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-11-17
 * 
 * 시스템에서 지원하는 국가코드 목록 Enum
 */
public enum NationCode {
	KOR("KOR")
	;
	private String code;
	
	NationCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
