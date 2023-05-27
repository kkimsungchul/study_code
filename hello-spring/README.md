# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
- URL : https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard

## 소개
- 인프런에 있는 "김영한 - 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술" 를 보고 따라하고, 공부한 프로젝트

## 내용
- Spring Boot로 프로젝트 생성
- 기본적인 회원가입 / 회원조회
- 테스트 케이스 작성 (단위테스트 / 통합테스트)
- 메모리를 사용한 사용자 관리
- 데이터베이스(JDBC)를 사용한 사용자 관리
- 데이터베이스(JdbcTemplate)를 사용한 사용자 관리

## 중요 내용 정리

- 생성자가 하나이면 @Autowired를 생략해도 된다.

- Repository 인터페이스 구현
```text
Repository를 인터페이스로 구현하여 메모리,JDBC , JDBCTemplate 등으로 새로운 구현체를 만들때마다 코드를 수정하지않고
연결을 설정하는 부분만 변경하면 됨
이부분이 진짜 중요한거 같음,
※ 인터페이스를 정의 하지 않고 직접 구현했다고 하면, 새로운 데이터베이스나 연결 방식을 사용할 경우 다 다시 변경해야함..
```

- 개방-폐쇄 원칙(OCP , Open-Closed Principle) 
```text
확장에는 열려있고, 수정, 변경에는 닫혀있다.
```
- 스프링의 DI (dependenices Injection)을 사용하면 "기존코드를 전혀 손대지 않고, 설정만으로 구현 클래스 변경" 할수 있다.
- JDBC
```text
DB연결부터 해제까지 모든것을 작성해야 함.
```
- JdbcTemplate
```text
순수 JDBC와 동일한 환경 설정을 하면 됨
Spring JdbcTemplate과 Mybatis 같은 라이브러리는 JDBC API 에서 반복코드를 대부분 제거해준다.
하지만 SQL은 직접 작성해야 한다.
템플릿패턴(디자인패턴)을 이용하여 구현한 JDBC임
```

### 기타 참고 사항
- 로컬 PC에 H2 데이터베이스를 설치 하지 않고 스프링 내장 H2로 작동하도록 구현
