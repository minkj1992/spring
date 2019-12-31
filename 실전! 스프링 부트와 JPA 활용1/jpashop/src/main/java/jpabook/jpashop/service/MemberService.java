package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //��ȸ�� ���� ����ȭ, �� �����ɿ����� �������־�� �Ѵ�.
@RequiredArgsConstructor    // final field �ʱ�ȭ���ش�. @Autowired�� ���������ϸ�, ���� ������ �ѹ� �ʱ�ȭ �۵��Ѵ�.
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired  //�������� �ֽ� ���� spring����
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * ȸ�� ����
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); // �ߺ�ȸ�� ����
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
        }
    }

    // ȸ�� ��ü ��ȸ
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // ȸ�� ���� ��ȸ
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

