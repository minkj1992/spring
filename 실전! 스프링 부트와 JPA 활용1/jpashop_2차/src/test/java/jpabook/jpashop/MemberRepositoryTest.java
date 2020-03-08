//package jpabook.jpashop;
//
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    // Transactional : 1) 이게 없으면 Assert error 2) test에 존재하면 알아서 Rollback을 해준다. (실제 h2 db는 비워진 상태)
//    @Transactional
//    @Rollback(false)
//    public void testMember() throws Exception{
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//        //when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//
//        //JPA 엔티티 동일성 보장
//        // 같은 트랜잭션안에서 저장과 조회한 엔티티는 영속성 context가 같기때문에 id가 같다면 같은 엔티티로 취급한다.
//        // 1차 캐시내에서 관리하던 녀석이 나온다. (그렇기 때문에 select query도 나가지 않고 insert 쿼리만 나간다.)
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//}