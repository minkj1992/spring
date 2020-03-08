package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="orders")
@Getter @Setter
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
}
