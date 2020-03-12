package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Service class는 repository에 단순히 위임만 하는 클래스
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // save
    @Transactional
    public void saveItem(Item item) { itemRepository.save(item); }

    @Transactional
    public Item updateItem(Long itemId, int price, String name, int stockQuantity) {
        // 준영속성 context -> 변경감지 ( merge는 위험하다 )
        Item findItem = itemRepository.findOne(itemId);
        findItem.change(price, name, stockQuantity);
        // @Transactional안에서 Entity를 다시 조회하고 값을 변경한다면 트랜잭션 commit 시점에 Dirty Checking이 일어나서 1차 캐시 값이 snapshot과 비교하여 UPDATE Query 일어난다.
        return findItem;
    }

    //조회
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.finadAll();
    }

}
