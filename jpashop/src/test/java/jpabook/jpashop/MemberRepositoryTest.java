//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//
//옛날에 만든 테스트임.
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)//테스트 후 데이터 롤백 안함
//    public void testMember(){
//
//        //given
//        Member member = new Member();
//        member.setName("김성철");
//
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.findMember(saveId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
//
//        Assertions.assertThat(findMember).isEqualTo(member);//같음, 같은 영속성 컨텍스트 안에 있음
//        System.out.println("### findMember == member : " +  (findMember == member));
//
//
//
//
//    }
//
//
//}