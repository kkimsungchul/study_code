package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true) //Transactional 어노테이션은 두개이며, 스프링을 사용할 경우 스프링에서 제공하는 Transactional 을 사용할 것을 권장
@RequiredArgsConstructor//final이 있는 filed 들을 가지고 생성해줌
public class MemberService {


    //필드는 final 하는게 좋음, 초기화 하지 않을 경우 오류가 발생함, 테스트 케이스 작성시에 좋음
    private final MemberRepository memberRepository;


    

    //2
    //생성 시점에 주입 가능
    //생성자가 하나일 경우에는 Autowired 를 붙이지 않아도 됨
    //@Autowired
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }


    //1
    //setter injection
    //장점 : 테스트 코드 작성시 MOK 객체를 직접 주입할 수 있음
    //단점 : Runtime 시점에 해당 객체를 바꿀수가 있음
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     * */
    @Transactional//false 가 기본 값임
    public Long join (Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        //EXCEPTION
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //회원 전제 조회
    //@Transactional(readOnly = true)//읽기 일때는 성능에 이점을 가짐
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한건 조회
    //@Transactional(readOnly = true)//읽기 일때는 성능에 이점을 가짐
    public Member findOnd(Long id){
        return memberRepository.findOne(id);
    }



}
