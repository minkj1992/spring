package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 기본적으로 jpa에서 데이터 변경은 @Transactional 안에서 이뤄져야함.
// 읽기만 하는 method를 DB상에서 최적화 시켜줌
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    /** [INJECTION 1: FIELD]
     *  단점: 이렇게 하면 바꾸질 못한다(test시)
     */
//     @Autowired
//     private MemberRepository memberRepository;
    /** [INJECTION 2: SETTER]
     *  장점: Mock 객체를 args에 넣어주어 테스트 가능하다.
     *  단점: 다시 호출하여 MemberRepository가 변경될 위험이 존재
     */
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /** [INJECTION 3: Constructor]
        어차피 setter 또한 생성될 시점에 1번만 불러지니 생성자로 대체한다.
        최신 버전에서는 @Autowired를 생략해도 된다.
     */
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /** [INJECTION 4: Lombok 적용]
     @RequiredArgsConstructor: final 설정된 field들만 @Autowired를 생성시점에 적용해준다.
     */
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    // default가 readOnly false
    @Transactional
    public Long join(Member member) {
        valiDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId(); // persist 이후 항상 id가 존재한다가 보증됨
    }

    //EXCEPTION
    private void valiDuplicatedMember(Member member) {
        // 분산환경 (멀티스레딩) 고려한다면 member.getName에 Unique 제약조건을 두어 최후의 보류 해야한다.
        // 같은 타이밍에 같은 이름의 member를 넣을 경우 class 자체에 lock을 걸지말고, unique constraint on your table 해주어
        // ""each time I try to insert a row in this table, please check that there is no existing row with this combination of columns the same" 검사해준다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
