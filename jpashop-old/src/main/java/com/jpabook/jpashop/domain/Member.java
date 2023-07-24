package com.jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity //필수 
//@Table(name="MEMBER")//테이블명이 다를 때 해당 테이블명으로 지정
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    //@Column(length = 10)
    private String name;

    @Embedded// 해당 클래스에 @Embeddable 가 있으면 생략 가능함, 명확하게 하기위해 명시
    private Address address;

    @OneToMany(mappedBy ="member")
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
