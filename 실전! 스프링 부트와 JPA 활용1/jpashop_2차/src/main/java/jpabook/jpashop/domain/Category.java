package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    // 다대다관계는 joinTable이 필요하다
    // 객체는 collection : collection으로 다대다를 가질 수 있지만, 관계형 DB는 Collection관계를 양쪽에서 가지지 못하기 때문 (1대다 다대1로 풀어주는 중간 table 필요)
    // 실무에서는 사용하지 않는게 filed를 더 추가하지 못한다.
    // joinColumns = : 중간 테이블에있는 category_id
    // inverseJoinColumns = : 중간 테이블의 item에 들어갈 요소
    @JoinTable(name = "category_item",
            joinColumns= @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 카테고리 계층 구조(tree, recursive 구조)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // mappedBy에 parent를 넣어준다. 이 parent가 child가 가진 parent일까? 아니면 this 클래스의 parent일까?
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메소드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
