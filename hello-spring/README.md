# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 - 김영한
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




- 어노테이션 DI(의존성주입) 설명
- DI에는 생성자 주입, 필드 주입 ,setter 주입 세가지가 있음
- 상황에 따라 구현 클래스를 변경해야 할 경우 설정을 통해 스프링 빈으로 등록
- 자바 코드로 직접 스프링 빈 등록하기 , 순수 JDBC < 해당 챕터 참고
```java
	//필드 주입은 수정할수 있는 방안이 없음 
	@Controller
	public class MemberController {
		@Autowired
		private final MemberService memberService;
	}
```

```java
	///setter 주입은 접근지시자가 public임
	@Controller
	public class MemberController {    
		private MemberService memberService;

		@Autowired
		public void setMemberService(MemberService memberService) {
			this.memberService = memberService;
		}
	}
```

```java
	//생성자 주입은 어플리케이션 실행 시 한번만 호출되면서 끝남
	@Controller
	public class MemberController {

		private final MemberService memberService;

		@Autowired
		public MemberController(MemberService memberService) {
			this.memberService = memberService;
		}
	}
```

- Bean 등록 방법
```text
정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용함.
정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록함.
```

```java
        //어노테이션 사용
		//등록하려는 클래스 위에 @Controller , @Service , @Repository 사용
		@Service
		public class MemberService {

		}
```
```java
	    //JavaConfig 사용 방법
		@Configuration
		public class SpringConfig {

			@Bean
			public MemberService memberService(){
				return new MemberService(memberRepository());
			}

			@Bean
			public MemberRepository memberRepository() {
				return new MemoryMemberRepository();
			}
		}
		
```
- JPA
```text
저장, 조회, 업데이트는 쿼리를 짤 필요가 없음
PK기반이 아닌 나머지 쿼리는 작성해줘야함(JPQL)
JPA는 저장,업데이트 할떄 때 트랜잭션 안에서 실행해야함(@Transactional)
```
- JPA 자동 주입
```java
    //JPA 자동주입 1
    //@PersistenceContext
    private EntityManager em;

    //JPA 자동주입 2
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
```

- Spring Data JPA
```text
리포지토리 구현 클래스 없이 인터페이스 만으로 개발 완료 가능
CRUD 기능도 스프링 데이터 JPA가 모두 제공함
스프링 데이터 JPA 를 사용하면 자동으로 스프링이 빈에 등록을 함
```
```text
interface 를 생성하고 JpaRepository를 상속받아 놓으면 Spring data가 알아서 구현하고 스프링 빈에 등록을함
```
```text
interface SpringDataJpaMemberRepository extends JpaRepository 
처럼 JpaRepository 를 구현하면 자동으로 스프링Bean에 등록됨
등록된 내용은 SpringConfg의  memberRepository에 자동 주입됨
private final MemberRepository memberRepository; //여기에 SpringData JPA 가 자동으로 주입을 해줌
```
```java
//JPA 사용시 SpringConfig
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository; //여기에 SpringData JPA 가 자동으로 주입을 해줌

    //생성자 하나일경우 autowired 생략 가능
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
```
```text
기본적인 CRUD , 단순 조회 ( INSERT , DELETE , SELECT * 등 ), 페이징 기능 제공
다만, 공통 interface 이기때문에 비지니스에 따른 메소드는 직접 생성해야함
생성 규칙은 findBy찾을필드명 ex)findByName
여러개일 경우 findBy찾을필드명And찾을필드명 ex) findByNameAndId
```
```text
실무에서는 JPA와 스프링 JPA를 기본으로 사용하고 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용함
Querydsl 을 사용하면 쿼리도 자바 코드로 안전하게 작성이 가능하며, 동적 쿼리도 편리하게 적상할수있음.
이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링JDBCTemplate를 사용하면 됨
```

- 데이터 접근 방법
```text
- 순수 JDBC
쿼리량이 어마어마함
- 스프링 JDBCTemplate
반복되는 코드가 줄어들지만 쿼리는 직접 작성해야함
- JPA
기본적인 CRUD에 대한 쿼리를 작성할 필요가 없음
- Spring Data JPA
구현 클래스를 작성할 필요 없이 interface만으로 끝남
```

- AOP가 필요한 경우
```text
모든 메소드의 호출 시간을 측정하고 싶다면?
공통 관심 사항 (cross-cutting concern) vs 핵심 관심사항(core concern)
회원 가입 시간, 회원 조회시간을 측정 하고싶다면?
```
- AOP(Aspect Oriented Programming, 관점 지향 프로그래밍) 적용
```text
공통 관심 사항, 핵심 관심 사항 분리
```
- AOP 적용
```java
@Aspect
//SpringConfig 에 Bean 으로 등록, 
//해당 어노테이션을 사용하지 않을 경우 SpringConfig 에 직접 설정해주면됨
@Component
public class TimeTraceAop {

    //적용할 패키지
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START  : " + joinPoint.toLongString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END  : " + joinPoint.toLongString() + " " + timeMs + "ms");
        }

    }
}
```
- AOP 작동 원리
```text
- 기존
Controller -> Service
- 적용
Controller -> 프록시(Service, 가짜 서비스) -> Serivce

```

### 기타 참고 사항
- 로컬 PC에 H2 데이터베이스를 설치 하지 않고 스프링 내장 H2로 작동하도록 구현
