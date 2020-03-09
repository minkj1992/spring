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
}
