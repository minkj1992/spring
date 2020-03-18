package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    // JPA연관관계의 주인: foreign_key와 가까운곳으로 설정, 연관관계의 주인은 그대로 두고, 거울부분에 명시해준다.
    // order table에 있는 member 필드 이름, 읽기 전용으로 생성됨, 여기에 값을 넣는다고해서 외래키가 변경되지 않는다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
