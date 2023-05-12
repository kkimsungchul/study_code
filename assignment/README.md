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


