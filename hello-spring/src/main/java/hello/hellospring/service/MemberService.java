package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


/**
 * Member service
 */
public class MemberService {

    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return member.getId();
     */
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
//        long start = System.currentTimeMillis();
//        try{
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long times = finish - start;
//            System.out.println("join = " + times + "ms");
//
//        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미존재하는 회원입니다");
                });
    }

    /**
     * 전체 회원 조회
     * @return members
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long memberId) {
        return memberRepository.findById(memberId);
    }


}
