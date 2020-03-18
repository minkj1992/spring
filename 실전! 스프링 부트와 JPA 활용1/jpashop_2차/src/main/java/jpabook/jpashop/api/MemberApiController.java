package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;


@RestController //@Controller @ResponseBody
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());


        return new Result(collect); //한번 class로 감싸서 내보내야 json이 유연해진다. 배열타입으로 바로 내보내면 JSON은 array타입으로 받는다.
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    // @RequestBody는 json형식으로 감싸준다고 생각하면 된다.
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }




    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request
    ) {
        // update는 변경감지!
        memberService.update(id, request.getName());    // command, 여기에서 Member를 return해주어도 되지만 command와 query는 분리한다. 트랜잭션 끝난시점
        // command와 query는 분리한다.
        Member findMember = memberService.findOne(id); //query
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }


    //DTO
    // Entity와 Presentation계층을 분리하고, REST API에 강제하고 싶은 값은 DTO단에서 Validation강제해주어 모든 통신에서 처리되도록 한다. (부분 커스터마이징 가능)
    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;    //@DATA의 역할은? 생성자 만들어주지 않았는데 Request에 값이 저장되는 마법..
    }

    //DTO
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @Data   // STATIC 사용하는 이유는?
    @AllArgsConstructor //Entity에서는 @Getter정도만 사용하지만, DTO에는 좀 막쓴다.
    static class UpdateMemberResponse {
        private Long id;
        private String name;

    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }
}
