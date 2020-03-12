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

    // MemberRepo에서는 왜 그냥 save 했을까?
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            //merge는 attribute를 set을 통해서 다 갈아주기 때문에, 변경되지 말아야 할 녀석이 있다면
            //, 실수로 값이 null 또는 error값으로 변경될 수 있다. merge를 하면 안된다.
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

