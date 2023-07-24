package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        Helloe helloe = new Helloe();
        helloe.setData("hihi");
        String data = helloe.getData();
        System.out.println(data);

        SpringApplication.run(JpashopApplication.class, args);
    }

}
