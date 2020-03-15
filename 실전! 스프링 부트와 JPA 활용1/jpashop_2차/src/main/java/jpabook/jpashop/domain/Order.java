package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Slf4j
@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //Order을 new를 통하여 생성하지 못하도록 막는다.
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // **모든 연관관계는 지연로딩으로 설정!!!**
    // xToOne은 디폴트가 FetchType.EAGER이고 이는, 관계된 table을 모두 끌고 들어온다. (추적하기 어렵다.)
    // 그러므로 xToOne은 명시적으로 lazyLoading 설정해주어야 한다.
    @ManyToOne(fetch = LAZY)
    // foreign key이름 설정, DB설계시에 외래키는 항상 many쪽이 가지도록 한다.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // 원래는 entity당 persist를 해주어야한다. ex) persist(OrderItem1),persist(OrderItem2) ... -> 이후 persist(order).
    // 하지만 cascade를 사용하면 알아서 해준다. persist(order)하면 알아서 나머지 OrderItem들 persist해준다.
    private List<OrderItem> orderItems = new ArrayList<>();

    //Delivery 보다 Order에 더 많은 access가 일어날 것이기 때문에 fk를 여기에 둔다. -> 연관관계의 주인
    // order persist시 delivery persist해준다.
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    // 양방향 관계에서 DB상에서는 연관관계 주인에게 저장해주면 되지만, java instance는 둘 모두 저장시켜주는 것이 편리하다. 자동으로 저장해주도록 해주는 method
    // 이 메서드는 양쪽 중 핵심적으로 controll하는 class에 존재하는 것이 좋다.
    // 양방향 저장
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //=생성 메서드=//
    // 복잡한 생성은 별도의 생성메서드를 사용한다.
    // 해당 method에서 Order을 생성해주기때문에 static사용
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);  //양방향 setter
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //=비즈니스 로직=//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {  // status에 대해서 jpa가 query를 쏴준다.
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능 합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        log.info("REAL CANCEL HAPPENED");
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

//    public int getTotalPrice() {
//        int totalPrice = 0;
//        for (OrderItem orderItem: orderItems) {
//            totalPrice += orderItem.getTotalPrice();
//        }
//        return totalPrice;
//    }

    //=조회 로직=//

    /**
     * 주문 상품 가격 전체 조회
     */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
