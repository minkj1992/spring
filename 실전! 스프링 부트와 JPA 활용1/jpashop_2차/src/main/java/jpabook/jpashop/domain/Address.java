package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

// Address class의 구성요소들을 하나로 묶어서 객체로 생성 -> 이를 참조하여 생성하는 객체는 @Embedded
@Embeddable
@Getter
// JPA에서 기본적으로 생성자가 존재해야 리플렉션, 프록싱과 같은 기능을 할 수 있어, 기본 생성자를 만들어준다.
// 다만 protected까지 허용해주어, 사용자가 함부로 변경을 하지 못하도록 한다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 값타입은 생성할 때만 set 해주고, setter에 의해서 변경되면 안된다.
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
