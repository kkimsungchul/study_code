# 개발 환경 
- Windows 10
- IntelliJ IDEA Ultimate
- JAVA 1.8
- SpringBoot 2.6.14
- Gradle 7.6.1
- JUnit5


# DB 설계 
- stock - 종목
id,code,name,price
1,005930,삼성전자,61500

- stock_price - 가격
id, code ,price, time

- stock_views - 조회수
id, code , view , time

- stock_trading_volume - 거래량
id , code , volume , time

# Swagger URL
http://localhost:8080/swagger-ui/index.html

# 기타 문제해결 - 데이터 비교
H2 데이터베이스의 내장함수인 FORMATDATETIME() 을 사용하여 날짜를 비교할 경우 쿼리 조회시간이 100배 차이남.
FORMATDATETIME() 을 사용한 쿼리문 실행 시간
(20 rows, 13501 ms)
(20 rows, 13826 ms)
(20 rows, 13473 ms)

FORMATDATETIME() 을 사용하지 않은 쿼리문 실행 시간
(20 rows, 27 ms)
(20 rows, 22 ms)
(20 rows, 0 ms)



