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
    // �ٴ�ٰ���� joinTable�� �ʿ��ϴ�
    // ��ü�� collection : collection���� �ٴ�ٸ� ���� �� ������, ������ DB�� Collection���踦 ���ʿ��� ������ ���ϱ� ���� (1��� �ٴ�1�� Ǯ���ִ� �߰� table �ʿ�)
    // �ǹ������� ������� �ʴ°� filed�� �� �߰����� ���Ѵ�.
    // joinColumns = : �߰� ���̺��ִ� category_id
    // inverseJoinColumns = : �߰� ���̺��� item�� �� ���
    @JoinTable(name = "category_item",
            joinColumns= @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // ī�װ� ���� ����(tree, recursive ����)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // mappedBy�� parent�� �־��ش�. �� parent�� child�� ���� parent�ϱ�? �ƴϸ� this Ŭ������ parent�ϱ�?
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==�������� �޼ҵ�==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
