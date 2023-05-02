package com.jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name ="ORDER_ID")
    private Long id;

    @Column(name ="MEMBER_ID")
    private Long memberId;

    private Member member;
    
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)//스트링으로 꼭 지정
    private OrderStaus orderStaus;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStaus getOrderStaus() {
        return orderStaus;
    }

    public void setOrderStaus(OrderStaus orderStaus) {
        this.orderStaus = orderStaus;
    }
}
