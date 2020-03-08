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

    // enum타입 주의사항: Ordinal로 하면 숫자로 enum구별자가 저장된다.
    // 서비스 중간에 status가 추가되면 ordinal 했다면 망한다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP



}
