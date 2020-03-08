package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    // enumŸ�� ���ǻ���: Ordinal�� �ϸ� ���ڷ� enum�����ڰ� ����ȴ�.
    // ���� �߰��� status�� �߰��Ǹ� ordinal �ߴٸ� ���Ѵ�.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP



}
