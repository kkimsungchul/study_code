package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


//Spring Bean 으로 등록
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //Spring Entity Manager 를 자동 주입
    //JPA 를 순수하게 사용하면 해야 할 EntityManager Factory를 만들지 않아도 됨
    //lombok 을 사용하므로 Spring에서 자동으로 주입해줌
    //@Autowired 로 하면 안되고 @PersistenceContext 로 해야함, 하지만 스프링Data jpa 에서 Autowired 가 되도록 지원해줬음
    //@PersistenceContext
    private final EntityManager em;

//    public MemberRepository(EntityManager em){
//        this.em = em;
//    }
    /**
     * 멤버 저장
     * @param member 멤버 정보
     * */
    public void save(Member member){
        em.persist(member);
    };

    /**
     * 멤버 단건 조회
     * @param id 멤버 id
     * @return Member
     * */
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    /**
     * 멤버 전체 조회
     * @return List<Member> 멤버목록
     * */
    public List<Member> findAll(){
        return em.createQuery("select m from Member m " , Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name" , name)
                .getResultList();
    }

}
