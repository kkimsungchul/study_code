# 카카오페이증권 신용/대출 원장개발자 과제 
## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행](#빌드-및-실행)
- [요구사항 및 기술제약](#요구사항-및-기술제약)
- [요구사항 분석 및 설계](#요구사항-분석-및-설계)
- [DB 설계](#DB-설계)
- [요구사항 개발](#요구사항-개발)
- [기타 문제 해결](#기타-문제-해결)
---
## 개발 환경
### 기본 환경
- Windows 10
- IntelliJ IDEA Ultimate
### 서버 환경
- JAVA 1.8
- SpringBoot 2.6.14
- Gradle 7.6.1
- JUnit5
- H2 Database

---
## 빌드 및 실행
### 코드 내려 받기
git clone https://github.com/kakaopayseccoding-server/202305-kimsc1218-gmail.com.git

### 리눅스 빌드
```shell
cd assignment 
./gradlew clean build
```

### 윈도우 빌드
```shell
cd assignment
gradlew.bat clean build
```

### 실행
```shell
java -jar build/libs/assignment-0.0.1-SNAPSHOT.jar
```


### 서버 접속 URL
http://localhost:8080

### Swagger 접속 URL
http://localhost:8080/swagger-ui/index.html

---
## 요구사항 및 기술제약

### API 설명
- 주제는 “인기”, “상승”, “하락”, “거래량” 4가지로 분류됩니다.
- 화면에서 보이는 구매잔량/잔량차이/회전율은 과제 사항이 아닙니다.
- 요청은 페이징(기본 20건) 처리하여 조회합니다.
- 최대 100개까지만 데이터를 제공합니다.
- API 호출 시, 순위가 랜덤하게 변경될 수 있도록 합니다.

### 요구사항
- 서비스 제공에 필요한 RESTful API 를 구현합니다.
- 실시간 데이터의 변동을 위해 데이터가 랜덤으로 변경되는 API를 추가로 개발해 주시기 바랍니다.
- Application이 로딩될 때 기본 데이터가 DB에 적재하도록 합니다.
- 주식 종목에 대한 정보는 첨부된 데이터를 참고하시면 됩니다.
- 데이터 테이블 구조는 효율적인 방식으로 스스로 설계해 주시면 됩니다.
- 각 기능 및 제약사항에 대한 단위테스트를 작성합니다

### 기술제약
- 설계 내용과 이유, 핵심 문제해결 전략 및 분석한 내용을 작성하여 “readme.md” 파일에 첨부해주세요.
- 개발은 SpringBoot 와 JAVA를 사용해서 구현하시기 바랍니다.
- API의 HTTP Method들은 자유롭게 선택하시면 됩니다.
- 에러응답, 에러코드는 자유롭게 정의해주세요.
- 단위 테스트를 작성하세요.

---
## 요구사항 분석 및 설계
### API 분석
- 인기, 상승, 하락, 거래량 페이지에 보여지는 데이터는 모두 똑같으며, 정렬 기준만이 다름.
- 모든 페이지에는 "종목코드" , "종목명" , "현재가" , "등락률" 이 표시됨.
- 위 내용을 토대로 리턴할 부모 클래스(StockVO)를 생성하고, 각각의 페이지에서 필요로 하는 필드는 부모 클래스(StockVO)를 상속받아서 구현.

### 공통 API 설계
- 모든 데이터는 아래의 ResponseAPI 객체를 통하여 리턴되도록 구현.
```json
{
  "message": "success or fail",
  "data": {},
  "timestamp": "2023-05-13T12:22:55.907Z"
}
```
- 오류 발생 시 RestControllerAdvice 를 사용하여 오류메시지를 리턴하도록 설정.
```java
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ResponseAPI> exceptionHandler(HttpServletRequest request, final Exception e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseAPI("fail",e.getMessage(),LocalDateTime.now()));
    }
}
```


---
## DB 설계
- 데이터베이스는 H2(스프링부트 내장데이터베이스)를 사용.
- 테이블 생성과 초기 데이터는 data.sql , schema.sql 두개의 파일로 진행. 
- 데이터베이스 테이블은 종목, 가격, 조회수, 거래량 네개의 테이블로 나눠서 설계를 진행하여 모든테이블은 stock(종목 테이블)과 JOIN 하여 조회하도록 함.
- stock_price(가격) 테이블만 새로운 데이터를 insert 하고 stock_views(조회수) , stock_trading_volume(거래량) 테이블은 기존 데이터를 update 하는 방향으로 설계
```text
조회수와 거래량은 INSERT 를 하여도 상관 없으나, 별다른 제약사항이 없기에 update 로 구현
```

### 테이블 명세
- stock - 종목 테이블
```sql
    id bigint(10) NOT NULL AUTO_INCREMENT           --시퀀스
    code varchar(10)                                --종목코드
    name varchar(50)                                --종목명
    PRIMARY KEY (code)
```

- stock_price - 가격
```sql
    stock_code varchar(10)                          --종목코드
    price int(20)                                   --가격
    create_time timestamp NOT NULL default now()    --생성날짜
```

- stock_views - 조회수
```sql
    stock_code varchar(10)                          --종목코드
    view int(10)                                    --조회수
    create_time timestamp NOT NULL default now()    --생성날짜
    modify_time timestamp NOT NULL default now()    --수정날짜
```

- stock_trading_volume - 거래량
```sql
    stock_code varchar(10)                          --종목코드
    volume int(10)                                  --거래량
    create_time timestamp NOT NULL default now()    --생성날짜
    modify_time timestamp NOT NULL default now()    --수정날짜
```


---
## 요구사항 개발
### 개발 고려사항
- 모든 API에는 등락률이 표시되고 있으므로, 시초가와 현재가의 가격 차이를 계산해야함.
- 데이터 변경 API를 호출하여 가격,거래량,조회수를 무작위로 변경해야 함.
- 국내 주식의 특성상 상한가(+30%)와 하한가(-30%)를 고려해야함.

### 공통사항
- 페이징 처리(20개)로 되어 있으며1~5페이까지만 조회 가능하며 1보다 작을경우 첫페이지 5보다 클경우 마지막 페이지(5)를 표시하도록 함
- 페이지 값에 숫자가 아닌 문자가 들어올 경우 기본 1페이지를 표시되도록 함
- 페이지 값 없이 데이터를 조회할 경우 기본 1페이지가 표시되도록 함 (ex /view , /volume)
- 조회수와 거래량은 기존 데이터에 더하지 않고 매번 새로운 값으로 갱신 (1~9999사이)
- 가격의 경우 상한가와 하한가를 고려하여 시초가에서 +-30% 이내로만 변경되도록 처리
- 임의의 데이터 변경은 모두 데이터베이스 RAND() 함수를 사용하여서 처리하도록 함
```text
JAVA에서 처리할 경우 데이터를 불러 온 후 임의의 값으로 변경 후 INSERT 작업을 진행 해야함
  -JAVA 처리
  SELECT -> JAVA(데이터변경) -> INSERT or UPDATE
  -DB 처리
  SELECT -> UPDATE or INSERT

```
- 현재가 갱신시 계산 공식
```text
- select sp.price*truncate(0.7 + (RAND() * 0.6),4) from stock_price as sp
- 시초가*(0.7~1.3사이의 난수)
```
- 퍼센트 계산 공식
```text
- CONCAT (CAST((sp1.price - sp2.price) * 100.0 / sp2.price AS DECIMAL(10,2)),'%') as percent
- (현재가-시초가)*100/시초가 , 소수점 이하2자리까지표시 , 계산 후 % 문자열 더하기
```


### API 명세

#### ResultData 설명
```json
{"message": "success",      //API 조회 성공 여부 (success or fail)
  "data": [                 //응답데이터
  {
  "code": "12330",            //종목코드
  "name": "현대모비스",        //종목명
  "nowPrice": 206442,         //현재가
  "initPrice": 211000,        //시초가
  "strPrice": "206,442원",    //현재가(원 단위 환산)
  "percent": "-2.16%",        //등락률
  "color": "blue",            //등락률에 따른 색상
  "view": 9901,               //조회수
  "volume": 9902              //거래량
  },
  "timestamp": "2023-05-13T20:37:13.7866957"    //데이터 반환시간
  ]
}
```

### 데이터 랜덤 갱신
- URL : http://localhost:8080/stock/change
- Method : POST
- Data : 
```json
{
  "type": "all"   //all or view or volume or price
}
```
- ResultData :
```json
{
  "message": "success",
  "data": "all 데이터가 변경되었습니다.",
  "timestamp": "2023-05-13T20:57:52.7158605"
}
```

### 인기종목
- URL : http://localhost:8080/view/{page}
- Method : GET
- ResultData : 
```json
{
  "message": "success",
  "data": [
    {
      "code": "12330",
      "name": "현대모비스",
      "nowPrice": 206442,
      "initPrice": 211000,
      "strPrice": "206,442원",
      "percent": "-2.16%",
      "color": "blue",
      "view": 9901
    },
    {
      "code": "69500",
      "name": "KODEX 200",
      "nowPrice": 30246,
      "initPrice": 32915,
      "strPrice": "30,246원",
      "percent": "-8.11%",
      "color": "blue",
      "view": 9849
    },
    ...중략...
  ],
  "timestamp": "2023-05-13T20:37:13.7866957"
}
```

### 상승 종목
- URL : http://localhost:8080/rate/up/{page}
- Method : GET
- ResultData :
```json
{
  "message": "success",
  "data": [
    {
      "code": "11790",
      "name": "SKC",
      "nowPrice": 172104,
      "initPrice": 132500,
      "strPrice": "172,104원",
      "percent": "29.89%",
      "color": "red"
    },
    {
      "code": "207940",
      "name": "삼성바이오로직스",
      "nowPrice": 1134263,
      "initPrice": 875000,
      "strPrice": "1,134,263원",
      "percent": "29.63%",
      "color": "red"
    },...중략...
  ],
  "timestamp": "2023-05-13T20:51:25.3379745"
}
```

### 하락 종목
- URL : http://localhost:8080/rate/down/{page}
- Method : GET
- ResultData :
```json
{
  "message": "success",
  "data": [
    {
      "code": "64350",
      "name": "현대로템",
      "nowPrice": 18058,
      "initPrice": 25750,
      "strPrice": "18,058원",
      "percent": "-29.87%",
      "color": "blue"
    },
    {
      "code": "302440",
      "name": "SK바이오사이언스",
      "nowPrice": 86291,
      "initPrice": 122000,
      "strPrice": "86,291원",
      "percent": "-29.27%",
      "color": "blue"
    },...중략...
  ],
  "timestamp": "2023-05-13T20:52:28.023357"
}
```


### 거래량
- URL : http://localhost:8080/volume/{page}
- Method : GET
- ResultData :
```json
{
  "message": "success",
  "data": [
    {
      "code": "3410",
      "name": "쌍용C&E",
      "nowPrice": 5031,
      "initPrice": 7030,
      "strPrice": "5,031원",
      "percent": "-28.44%",
      "color": "blue",
      "volume": 9985
    },
    {
      "code": "90430",
      "name": "아모레퍼시픽",
      "nowPrice": 113549,
      "initPrice": 128000,
      "strPrice": "113,549원",
      "percent": "-11.29%",
      "color": "blue",
      "volume": 9964
    },
    ...중략...
  ],
  "timestamp": "2023-05-13T20:55:30.7413211"
}
```

---
## 기타 문제 해결
### 데이터 비교
- H2 데이터베이스의 내장함수인 FORMATDATETIME() 을 사용하여 날짜를 비교할 경우 쿼리 조회시간이 500배 차이나므로 서비스로직에서 오늘 날짜를 계산해서 넘겨주도록 하였습니다.<br>
```text
- FORMATDATETIME() 을 사용한 쿼리문 실행 시간
  - (20 rows, 13501 ms)
  - (20 rows, 13826 ms)
  - (20 rows, 13473 ms)

- FORMATDATETIME() 을 사용하지 않은 쿼리문 실행 시간
  - (20 rows, 27 ms)
  - (20 rows, 22 ms)
  - (20 rows, 0 ms)

```

### 하루 지난 시점의 데이터
- 쿼리문으로 오늘 날짜의 최초 값과 현재값을 비교하여 가져오도록 설계하여서, 날짜가 변경된 후에 신규 데이터가 없을 경우 데이터를 조회하지 못합니다.
- 스프링 스케줄러를 사용하여 0시 기준으로 어제 날짜의 마지막 데이터를 현재시간 기준으로 INSERT 하도록 구현하여 어제의 마지막 데이터가 오늘의 시초가가 되도록 하였습니다.

### 등락률 계산
- 시초가와 현재가를 DB에서 가져와 Java 에서 계산 후 Return 하도록 하였으나, 상승/하락의 경우에는 등락률 기준으로 데이터를 조회해야 하기때문에 DB에서 조회하여 가져올 때 계산하도록 변경하였습니다.

