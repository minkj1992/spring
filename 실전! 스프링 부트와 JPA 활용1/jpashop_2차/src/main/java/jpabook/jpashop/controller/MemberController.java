package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // @Valid를 붙여주면 MemberForm에 있는 @NotEmpty기능을 spring이 체크해준다.
    // Validate이후 BindingResult가 존재하면 오류가 담겨서 해당 변수로 실행가능
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            //thymeleaf-spring이라는 라이브러리가 다시 오류가 생성된 페이지로 보내진다.
            // th:class="${#fields.hasErrors('name')}? 'form-controlfieldError' : 'form-control'"> 와 같은 구문에 의해서
            // 자동으로 경고문을 띄워준다 (thymeleaf-spring lib)
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";    //redirect to home
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 사실 엔티티를 view에 뿌리는 것은 권장하지 않는다!!!
        // 로직이 복잡해지면, DTO나 form을 확장시키고 엔티티는 화면에 종속적이지 않도록 만드는 것이 중요하다
        // 하지만 여기에서는 화면에 뿌리더라도 entity를 수정하지 않아도 되는 상황이라 이렇게 했다.
        // **API를 만들때는 절대로 ENTITY를 그대로 반환해서는 절대 안된다, Entity에 logic을 추가하면 API의 스펙이 변하게 되버리는 현상 발생**
        model.addAttribute("members", memberService.findMembers()); //attribute는 어디에 사용?

        return "members/memberList";
    }

}
