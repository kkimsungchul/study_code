package hello.hellospring;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    //생성자 하나일경우 autowired 생략 가능
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }


//    //JPA 자동주입 1
//    //@PersistenceContext
//    private EntityManager em;
//
//    //JPA 자동주입 2
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }


    //JPA 사용하기전
//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //메모리DB
////        return new MemoryMemberRepository();
//        //JDBC 사용
////        return new JdbcMemberRepository(dataSource);
//        //JDBC Template 사용
////        return new JdbcTemplateMemberRepository(dataSource);
//        //JPA 사용
////        return new JpaMemberRepository(em);
//    }

    //DI에는 생성자 주입, 필드 주입 ,setter 주입 세가지가 있음
    // 필드 주입은 수정할수 있는 방안이 없음
    // setter 주입은 접근지시자가 public임
    // 생성자 주입은 어플리케이션 실행 시 한번만 호출되면서 끝남

    //# 개방-폐쇄 원칙(OCP , Open-Closed Principle)
    //확장에는 열려있고, 수정, 변경에는 닫혀있다.
    //# 스프링의 DI (dependenices Injection)을 사용하면 "기존코드를 전혀 손대지 않고, 설정만으로 구현 클래스 변경" 할수 있다.
}
