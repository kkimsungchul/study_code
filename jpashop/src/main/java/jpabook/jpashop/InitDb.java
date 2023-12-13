package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.doInit1();
        initService.doInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void doInit1(){
            Member member1 = createMember("userA", "서울","3333","4444");
            em.persist(member1);

            Book book1 = createBook("JPA1 BOOK" , 50000 , 1220);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK" , 10000 , 10);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 10000, 2);
            Delivery delivery = getDelivery(member1);
            Order order = Order.createOrder(member1, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void doInit2(){
            Member member2 = createMember("userB", "부산시","111","222");
            em.persist(member2);

            Book book3 = createBook("SPRING1 BOOK" , 10000 , 300);
            em.persist(book3);
            Book book4 = createBook("SPRING2 BOOK" , 20000 , 500);
            em.persist(book4);

            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 30000, 1);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 40000, 1);
            Delivery delivery2 = getDelivery(member2);
            Order order2 = Order.createOrder(member2, delivery2, orderItem3, orderItem4);
            em.persist(order2);
        }


        private static Delivery getDelivery(Member member1) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member1.getAddress());
            return delivery;
        }

        private Member createMember(String userName, String city , String street , String zipcode) {
            Member member1 = new Member();
            member1.setName(userName);
            member1.setAddress(new Address(city, street, zipcode));
            return member1;
        }

        private Book createBook(String name , int price , int stockQuantity){
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }



    }
}


