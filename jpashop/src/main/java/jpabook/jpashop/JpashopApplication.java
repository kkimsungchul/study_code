package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }


    //강제로 LAZY Loading 실행
    //Entity 가 노출됨,
    //성능상 문제가 있음
    //아래의 옵션을 사용하려면 양방향 연관관계에서 한곳에 @JsonIgnore 옵션을 줘야함
    @Bean
    Hibernate5Module hibernate5Module(){
        Hibernate5Module hibernate5Module = new Hibernate5Module();
//        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true);
        return hibernate5Module;
    }
}
