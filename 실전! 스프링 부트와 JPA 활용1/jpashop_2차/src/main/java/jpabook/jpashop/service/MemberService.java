package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// �⺻������ jpa���� ������ ������ @Transactional �ȿ��� �̷�������.
// �б⸸ �ϴ� method�� DB�󿡼� ����ȭ ������
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    /** [INJECTION 1: FIELD]
     *  ����: �̷��� �ϸ� �ٲ��� ���Ѵ�(test��)
     */
//     @Autowired
//     private MemberRepository memberRepository;
    /** [INJECTION 2: SETTER]
     *  ����: Mock ��ü�� args�� �־��־� �׽�Ʈ �����ϴ�.
     *  ����: �ٽ� ȣ���Ͽ� MemberRepository�� ����� ������ ����
     */
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /** [INJECTION 3: Constructor]
        ������ setter ���� ������ ������ 1���� �ҷ����� �����ڷ� ��ü�Ѵ�.
        �ֽ� ���������� @Autowired�� �����ص� �ȴ�.
     */
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /** [INJECTION 4: Lombok ����]
     @RequiredArgsConstructor: final ������ field�鸸 @Autowired�� ���������� �������ش�.
     */
    private final MemberRepository memberRepository;

    /**
     * ȸ�� ����
     */
    // default�� readOnly false
    @Transactional
    public Long join(Member member) {
        valiDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId(); // persist ���� �׻� id�� �����Ѵٰ� ������
    }

    //EXCEPTION
    private void valiDuplicatedMember(Member member) {
        // �л�ȯ�� (��Ƽ������) ����Ѵٸ� member.getName�� Unique ���������� �ξ� ������ ���� �ؾ��Ѵ�.
        // ���� Ÿ�ֿ̹� ���� �̸��� member�� ���� ��� class ��ü�� lock�� ��������, unique constraint on your table ���־�
        // ""each time I try to insert a row in this table, please check that there is no existing row with this combination of columns the same" �˻����ش�.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
        }
    }
    /**
     * ȸ�� ��ȸ
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
