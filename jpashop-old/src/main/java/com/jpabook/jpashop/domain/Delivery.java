package com.jpabook.jpashop.domain;


import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery" , fetch = LAZY)
    private Order order;

    private DeliveryStatus status;

    @Embedded// 해당 클래스에 @Embeddable 가 있으면 생략 가능함, 명확하게 하기위해 명시
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
