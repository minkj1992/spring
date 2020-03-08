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
//    // Transactional : 1) �̰� ������ Assert error 2) test�� �����ϸ� �˾Ƽ� Rollback�� ���ش�. (���� h2 db�� ����� ����)
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
//        //JPA ��ƼƼ ���ϼ� ����
//        // ���� Ʈ����Ǿȿ��� ����� ��ȸ�� ��ƼƼ�� ���Ӽ� context�� ���⶧���� id�� ���ٸ� ���� ��ƼƼ�� ����Ѵ�.
//        // 1�� ĳ�ó����� �����ϴ� �༮�� ���´�. (�׷��� ������ select query�� ������ �ʰ� insert ������ ������.)
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//}