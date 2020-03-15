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
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //Order�� new�� ���Ͽ� �������� ���ϵ��� ���´�.
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // **��� ��������� �����ε����� ����!!!**
    // xToOne�� ����Ʈ�� FetchType.EAGER�̰� �̴�, ����� table�� ��� ���� ���´�. (�����ϱ� ��ƴ�.)
    // �׷��Ƿ� xToOne�� ��������� lazyLoading �������־�� �Ѵ�.
    @ManyToOne(fetch = LAZY)
    // foreign key�̸� ����, DB����ÿ� �ܷ�Ű�� �׻� many���� �������� �Ѵ�.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // ������ entity�� persist�� ���־���Ѵ�. ex) persist(OrderItem1),persist(OrderItem2) ... -> ���� persist(order).
    // ������ cascade�� ����ϸ� �˾Ƽ� ���ش�. persist(order)�ϸ� �˾Ƽ� ������ OrderItem�� persist���ش�.
    private List<OrderItem> orderItems = new ArrayList<>();

    //Delivery ���� Order�� �� ���� access�� �Ͼ ���̱� ������ fk�� ���⿡ �д�. -> ���������� ����
    // order persist�� delivery persist���ش�.
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //�ֹ����� [ORDER, CANCEL]

    //==�������� �޼���==//
    // ����� ���迡�� DB�󿡼��� �������� ���ο��� �������ָ� ������, java instance�� �� ��� ��������ִ� ���� ���ϴ�. �ڵ����� �������ֵ��� ���ִ� method
    // �� �޼���� ���� �� �ٽ������� controll�ϴ� class�� �����ϴ� ���� ����.
    // ����� ����
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

    //=���� �޼���=//
    // ������ ������ ������ �����޼��带 ����Ѵ�.
    // �ش� method���� Order�� �������ֱ⶧���� static���
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);  //����� setter
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //=����Ͻ� ����=//
    /**
     * �ֹ� ���
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {  // status�� ���ؼ� jpa�� query�� ���ش�.
            throw new IllegalStateException("�̹� ����� �Ϸ�� ��ǰ�� ��Ұ� �Ұ��� �մϴ�.");
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

    //=��ȸ ����=//

    /**
     * �ֹ� ��ǰ ���� ��ü ��ȸ
     */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
