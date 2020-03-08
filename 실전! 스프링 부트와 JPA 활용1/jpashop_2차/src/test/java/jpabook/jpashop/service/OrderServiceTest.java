package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void ��ǰ�ֹ�() throws Exception{
        //given
        Member member = createMember("���ο�",new Address("��⵵", "����������", "156"));
        Book book = createBook("������ ��Ʈ�� �н��ϴ� MSA", 10000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("��ǰ �ֹ��� ���´� ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� �Ѵ�.", 1, getOrder.getOrderItems().size());
        assertEquals("�ֹ� ������ ���� * �����̴�.", orderCount*10000, getOrder.getTotalPrice());
        assertEquals("�ֹ� ������ŭ ��� �پ���Ѵ�.", 8, book.getStockQuantity());


    }


    @Test(expected = NotEnoughStockException.class)
    public void ��ǰ�ֹ�_������_�ʰ�() throws Exception{
        //given
        Member member = createMember("���ο�",new Address("��⵵", "����������", "156"));
        Book book = createBook("������ ��Ʈ�� �н��ϴ� MSA", 10000, 10);
        int orderCount = 11;

        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("��� ���� ���� ���� �߻��ؾ� �Ѵ�.");
    }


    @Test
    public void �ֹ����() throws Exception{
        //given
        Member member = createMember("���ο�",new Address("��⵵", "����������", "156"));
        Book book = createBook("������ ��Ʈ�� �н��ϴ� MSA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("�ֹ� ��ҽ� ���´� CANCEL", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("�ֹ��� ��ҵ� ��ǰ�� �׸�ŭ ��� �����ؾ� �Ѵ�.", 10, book.getStockQuantity());
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        em.persist(member);
        return member;
    }

}