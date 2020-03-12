package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items); //view에게 인자와 변수를 전달

        return "order/orderForm";
    }


    @PostMapping("order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        // 물론 controller에서 update를 할 수 있지만, controller에서는 핵심비지니스 로직을 넘기기보다
        // service 단에서 id 값들을 받는다면, 이에 대한 Entity 수정부터 생성까지 처리하는 것이 @Transactional상에서
        // 처리할 수 있어, Context 영속성 처리도 받으면서 훨씬 로직이 깔끔해진다. (dirty checking과 같은 서비스 받을 수 있다.)
        Long orderId = orderService.order(memberId, itemId, count);
        return "redirect:/orders";  //추후 결과 페이지 또는 결제 페이지로 넘어간다면 id값을 사용해야 한다.
    }

    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
