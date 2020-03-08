package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

// spring�� �����ؼ� test�����ڴ�.
@RunWith(SpringRunner.class) // junit�� spring�� �Բ� �����ҷ�?
@SpringBootTest //SpringBoot�� ��� ���¿��� �׽�Ʈ�� �����ϰڴ�. (���ٸ� Autowired�� �� �����Ѵ�.)
@Transactional // �⺻������ test entity������ rollback�� �������ش�.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void ȸ������() throws Exception{
        //given
        Member member = new Member();
        member.setName("Je");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); Transactional�� Rollback�Ǳ����� insert���� ������ �� Ȯ��
        assertEquals(member,memberRepository.findOne(savedId));
    }
//
//    @Test
//    public void �ߺ�_ȸ��_����() throws Exception{
//        //given
//        Member member1 = new Member();
//        member1.setName("kim");
//
//        Member member2 = new Member();
//        member2.setName("kim");
//
//        //when
//        memberService.join(member1);
//        try {
//            memberService.join(member2); // ���� �̸� ���� �߻��ؾ� �Ѵ�.
//        } catch (IllegalStateException e) {
//            // ���� �ڵ��� exception ���ָ� ���� ����
//            return;
//        }
//        //then
//        // assert.fail()�� �ڵ尡 ���� ���⿡ ���� �ȵȴٰ� �������ִ� ��, Ȥ�ó� test case�� �����ϴ� logic�� �߸�¥�� true�� �Ǵ� ���� ����
//        fail("���ܰ� �߻��ؾ� �Ѵ�.");
//    }

    @Test(expected = IllegalStateException.class)
    public void �ߺ�_ȸ��_����() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // ���� �̸� ���� �߻��ؾ� �Ѵ�.

        //then
        fail("���ܰ� �߻��ؾ� �Ѵ�.");
    }


}