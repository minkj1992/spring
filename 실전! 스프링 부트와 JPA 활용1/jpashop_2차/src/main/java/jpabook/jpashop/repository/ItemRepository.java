package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // MemberRepo������ �� �׳� save ������?
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            //merge�� attribute�� set�� ���ؼ� �� �����ֱ� ������, ������� ���ƾ� �� �༮�� �ִٸ�
            //, �Ǽ��� ���� null �Ǵ� error������ ����� �� �ִ�. merge�� �ϸ� �ȵȴ�.
            em.merge(item);
        }
    }
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> finadAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}

