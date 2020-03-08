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

// spring과 통합해서 test돌리겠다.
@RunWith(SpringRunner.class) // junit과 spring을 함께 실행할래?
@SpringBootTest //SpringBoot를 띄운 상태에서 테스트를 진행하겠다. (없다면 Autowired가 다 실패한다.)
@Transactional // 기본적으로 test entity에서는 rollback을 진행해준다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Je");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); Transactional이 Rollback되기전에 insert문이 쏴지는 지 확인
        assertEquals(member,memberRepository.findOne(savedId));
    }
//
//    @Test
//    public void 중복_회원_예외() throws Exception{
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
//            memberService.join(member2); // 같은 이름 예외 발생해야 한다.
//        } catch (IllegalStateException e) {
//            // 만약 코드대로 exception 쏴주면 정상 종료
//            return;
//        }
//        //then
//        // assert.fail()은 코드가 절대 여기에 오면 안된다고 선언해주는 것, 혹시나 test case를 생성하다 logic을 잘못짜서 true가 되는 것을 방지
//        fail("예외가 발생해야 한다.");
//    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 같은 이름 예외 발생해야 한다.

        //then
        fail("예외가 발생해야 한다.");
    }


}