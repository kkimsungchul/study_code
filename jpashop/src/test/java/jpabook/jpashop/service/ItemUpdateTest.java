package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)//junit5에서는 runWith 가 없음
@SpringBootTest     //스프링컨테이너 안에서 테스트 진행
@Transactional  //테스트 케이스에 있으면 기본적으로 롤백함
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 1L);

        //tx
        book.setName("이름변경");


        //변경감지 == dirty checking
        // TX commit



    }
}
