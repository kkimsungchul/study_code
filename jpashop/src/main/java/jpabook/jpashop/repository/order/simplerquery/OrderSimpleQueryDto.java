package jpabook.jpashop.repository.order.simplerquery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderSatus;
    private Address address;

//    public OrderSimpleQueryDto(Order order) {
//        orderId = order.getId();
//        name = order.getMember().getName(); //LAZY 초기화
//        orderSatus = order.getStatus();
//        orderDate = order.getOrderDate();
//        address = order.getDelivery().getAddress(); //LAZY 초기화
//    }

    public OrderSimpleQueryDto(Long orderId , String name , LocalDateTime orderDate , OrderStatus orderSatus , Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderSatus = orderSatus;
        this.orderDate = orderDate;
        this.address = address;
    }
}