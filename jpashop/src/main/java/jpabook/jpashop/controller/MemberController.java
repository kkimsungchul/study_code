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
    public String creteForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        /*
        * memberForm 에서 validation 을 걸어준 부분에 걸리게되면 오류가 발생함
        * 그 오류를 BindingResult result에 담은뒤 코드가 실행됨
        * result 에는 memberForm도 담겨서 넘어가게 됨
        * */
        if(result.hasErrors()){
            return "/members/createMemberForm";
        }


        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping("/members")
    public String memberList(Model model){
        /*
        * 실무적으로 복잡해지면 Member Entity를 그대로 뿌리는것보단 DTO를 이용해서 뿌리는게 좋음
        * API를 만들때는 Entity를 반환하면 안됌
        * 1. API 스펙이 변함
        * 2. API에 정보가 노출됨
        * */
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}
