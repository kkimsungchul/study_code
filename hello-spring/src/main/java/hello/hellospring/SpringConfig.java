package hello.hellospring;


import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
    //DI에는 생성자 주입, 필드 주입 ,setter 주입 세가지가 있음
    // 필드 주입은 수정할수 있는 방안이 없음
    // setter 주입은 접근지시자가 public임
    // 생성자 주입은 어플리케이션 실행 시 한번만 호출되면서 끝남

    //# 개방-폐쇄 원칙(OCP , Open-Closed Principle)
    //확장에는 열려있고, 수정, 변경에는 닫혀있다.
    //# 스프링의 DI (dependenices Injection)을 사용하면 "기존코드를 전혀 손대지 않고, 설정만으로 구현 클래스 변경" 할수 있다.
}
