package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)//junit5에서는 runWith 가 없음
@SpringBootTest     //스프링컨테이너 안에서 테스트 진행
@Transactional  //테스트 케이스에 있으면 기본적으로 롤백함
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //flush 를 하고 싶을 때 EntityManager 를 주입받아서 em.flush를 하면됨
    @Autowired EntityManager em;

    @Test
    @Rollback(false)//롤백하지않고 커밋함, 해당 어노테이션이 없으면 insert 쿼리가 나가지 않음
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId =  memberService.join(member);

        //em.flush();
        //then
        assertEquals(member,memberRepository.findOne(saveId));
        //Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given

        Member member1  = new Member();
        member1.setName("kim1");

        Member member2  = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);

        //then
        //junit5에서 사용방식, junit4에서는 상단의 @Test어노테이션 옆에 expected 를 넣어주면됨
//        assertThrows(IllegalStateException.class, () ->{
//            memberService.join(member2);//예외발생해야함
//        });
//

        //IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(memberB));

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        fail("예외가 발생해야 한다.");



    }

}