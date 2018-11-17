# holidays-collector
공휴일 데이터 수집기

## Application info
* Spring boot 2.0.x
* Spring5 webflux
* thymeleaf
* lombok
* reactor-test
* jdk 1.8+

## Package hierarchy
* **collector**               --- Collectors bounded context  
  * **application**            --- User interface  
    * **controller**            --- Controller  
    * **dto**                   --- DTO (nationCode 별)  
      * koreanholidays...  
    * **service**                
      * HolidayCollectionService               --- application service    
      * HolidayCollectionServiceFactory        ----------service factory(noation code에 따라 service impl 반환)  
      * **impl**                 --- application service impl  
        * KoreaHolidaysCollectiongServiceImpl
  * **domain**                 --- Collectors application domain    
    * Holiday  
    * NationCode  
* **config**                  --- Application configuration  
* **utils**                   --- Application Utility  
