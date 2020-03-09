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
    // @Valid�� �ٿ��ָ� MemberForm�� �ִ� @NotEmpty����� spring�� üũ���ش�.
    // Validate���� BindingResult�� �����ϸ� ������ ��ܼ� �ش� ������ ���డ��
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            //thymeleaf-spring�̶�� ���̺귯���� �ٽ� ������ ������ �������� ��������.
            // th:class="${#fields.hasErrors('name')}? 'form-controlfieldError' : 'form-control'"> �� ���� ������ ���ؼ�
            // �ڵ����� ����� ����ش� (thymeleaf-spring lib)
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
