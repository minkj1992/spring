package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
// ��Ӱ��� ������ ����ش�. single page ����
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// item���� �������ֱ� ���� key
// name default DTYPE
@DiscriminatorColumn()
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //=����Ͻ� ����=//
    // setter���� stockQuantity�� �����Ű�� �ȵȴ�. oop������ ���ϴ�

    /**
     * stock ����
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock ����
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}

