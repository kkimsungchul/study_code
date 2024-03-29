package jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; //주문 가격
    
    private int count; //주문 수량

//    protected OrderItem(){
//        //new OrderItem 으로 생성하는것을 막기위ㅐ 작성
//          lombok의 @NoArgsConstructor(access = AccessLevel.PROTECTED) 로 대체 가능    
//    }


    //생성 로직
    public static OrderItem createOrderItem(Item item , int orderPrice , int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }


    //비즈니스 로직
    public void cancel() {
        getItem().addStock(count);//재고수량 원상복구

    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();//총 금액
    }
}
