MERGE INTO MST_HOLIDAY MH
USING (SELECT
		   'KOR'           NATION_CODE,
		   '2018-01-01'    HOL_DATE,
		   '1월1일'          HOL_NAME,
		   'Initial input' DESCRIPTION,
		   'Initial input' REASON,
		   'N'             DEL_FLAG,
		   SYSDATE         INPUT_TIME,
		   1628            USER_KEY
	   FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-02-15'    HOL_DATE,
					 '설날'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-02-16'    HOL_DATE,
					 '설날'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-02-17'    HOL_DATE,
					 '설날'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-03-01'    HOL_DATE,
					 '삼일절'           HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-05-05'    HOL_DATE,
					 '어린이날'          HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-05-07'    HOL_DATE,
					 '대체휴무일'         HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-05-22'    HOL_DATE,
					 '부처님오신날'        HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-06-06'    HOL_DATE,
					 '현충일'           HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-06-13'    HOL_DATE,
					 '전국동시지방선거'      HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-08-15'    HOL_DATE,
					 '광복절'           HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-09-23'    HOL_DATE,
					 '추석'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-09-24'    HOL_DATE,
					 '추석'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-09-25'    HOL_DATE,
					 '추석'            HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-09-26'    HOL_DATE,
					 '대체휴무일'         HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-10-03'    HOL_DATE,
					 '개천절'           HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-10-09'    HOL_DATE,
					 '한글날'           HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL
	   UNION ALL SELECT
					 'KOR'           NATION_CODE,
					 '2018-12-25'    HOL_DATE,
					 '기독탄신일'         HOL_NAME,
					 'Initial input' DESCRIPTION,
					 'Initial input' REASON,
					 'N'             DEL_FLAG,
					 SYSDATE         INPUT_TIME,
					 1628            USER_KEY
				 FROM DUAL) TAB
ON (MH.HOL_DATE = TAB.HOL_DATE AND MH.NATION_CODE = TAB.NATION_CODE)
WHEN MATCHED THEN UPDATE SET MH.HOL_NAME = TAB.HOL_NAME, MH.INPUT_tIME = TAB.INPUT_TIME
WHEN NOT MATCHED THEN INSERT (NATION_CODE, HOL_DATE, HOL_NAME, DESCRIPTION, REASON, DEL_FLAG, INPUT_TIME, USER_KEY) VALUES (TAB.NATION_CODE, TAB.HOL_DATE, TAB.HOL_NAME, TAB.DESCRIPTION, TAB.REASON, TAB.DEL_FLAG, TAB.INPUT_TIME, TAB.USER_KEY)