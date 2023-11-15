package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughtStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)//junit5에서는 runWith 가 없음
@SpringBootTest     //스프링컨테이너 안에서 테스트 진행
@Transactional  //테스트 케이스에 있으면 기본적으로 롤백함
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook("JPA 책" , 10000 , 10);

        int orderCount=2;

        //when
        Long orderId = orderService.order(member.getId() , book.getId() , 2);

        //then
        Order getOrder =  orderRepository.findOnd(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000*orderCount,getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다.");
        assertEquals(8,book.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");
    }




    @Test
    public void 상품주문_재고수량조과() throws Exception{
        Member member = createMember();
        Item item = createBook("JPA 책2" , 10000 , 10);
        int orderCount = 11;


        NotEnoughtStockException exception =
                assertThrows(NotEnoughtStockException.class, () -> {
            orderService.order(member.getId(),item.getId(),orderCount);
        },"need more stock");
        assertEquals("need more stock", exception.getMessage());

        //fail("재고 수량 부족");

    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook("JPA 책3" , 10000 , 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId() , book.getId() , orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder =  orderRepository.findOnd(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(),"주문 취소시 상태는 cancel 이다.");
        assertEquals(10, book.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");


    }

    private Book createBook(String name, int price , int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);
        return member;
    }
}