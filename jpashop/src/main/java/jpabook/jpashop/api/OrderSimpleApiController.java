package jpabook.jpashop.api;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplerquery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplerquery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName();//Lazy 강제 초기화, getMember만 하면 Proxy 객체임
            order.getDelivery().getAddress();//Lazy 강제 초기화
        }
        return all;
    }
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        //ORDER 2개
        // N +1 > 1 + 회원N + 배송N
//        쿼리가 총 1 + N + N번 실행된다. (v1과 쿼리수 결과는 같다.)
//        order 조회 1번(order 조회 결과 수가 N이 된다.)
//        order -> member 지연 로딩 조회 N 번 -> 영속성 컨텍스트에 있나 확인함, 없는 경에만 DB를 찌르므로 최악의 경우 N번
//        order -> delivery 지연 로딩 조회 N 번
//        예) order의 결과가 4개면 최악의 경우 1 + 4 + 4번 실행된다.(최악의 경우)
//        지연로딩은 영속성 컨텍스트에서 조회하므로, 이미 조회된 경우 쿼리를 생략한다.

        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
        return result;

    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    
    //재사용성이 떨어짐, API의 스펙이 리포지토리에 들어감
    //1. Entity -> DTO로 변환
    //2. 필요하면 페치조인으로 성능 최적화
    //3. 그래도 안되면 DTO로 직접 조회
    //4. 최후의 방법은 JPA가 제공하는 네이티브 SQL이나 스프링 JDBCTemplate을 사용하여 SQL을 사용
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4(){
        return orderSimpleQueryRepository.findOrderDtos();
    }


    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderSatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderSatus = order.getStatus();
            orderDate = order.getOrderDate();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }

}
