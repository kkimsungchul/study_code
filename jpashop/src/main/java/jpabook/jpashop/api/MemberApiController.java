package jpabook.jpashop.api;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    //아래의경우 entity를 수정할 경우 API 스펙이 변경됨
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    //API 스펙에 대해서 명확하게 보여줄수 있음
    //벨리데이션 체크를 할수 있음
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2 (@RequestBody @Valid CreateMemberRequest createMemberRequest){
        Member member = new Member();
        member.setName(createMemberRequest.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberRequest updateMemberRequest){

        memberService.update(id , updateMemberRequest.getName());
        Member findMember = memberService.findOnd(id);
        return new UpdateMemberResponse(findMember.getId() , findMember.getName());
    }

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        List<Member> members = memberService.findMembers();
        return members;
    }

    @GetMapping("/api/v2/members")
    public Result membersV2(){
        List<Member> findMembers = memberService.findMembers();

        List<MemberDto> collect = findMembers.stream()
                .map(m-> new MemberDto((m.getName())))
                .collect(Collectors.toList());
        return new Result(collect);
    }
    
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
    
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }


    @Data
    static class CreateMemberRequest{
        private String name;

    }

    @Data
    static class CreateMemberResponse{
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}

