package jpabook.jpashop.domain;
// Order와 Item의 중간 테이블

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //OrderItem을 new를 통하여 생성하지 못하도록 막는다.
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 당시 가격
    private int count;  //주문 수량

    //=생성 메서드=//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //=비즈니스 로직=//
    public void cancel() {
        getItem().addStock(count);
    }   // stock에 대해서 update query를 jpa가 쏴준다.

    //=조회 로직=//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
