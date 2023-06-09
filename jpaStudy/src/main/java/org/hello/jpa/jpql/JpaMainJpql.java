package org.hello.jpa.jpql;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMainJpql {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Team team = new Team();
            team.setName("TeamA");
            Team newTeam = new Team();
            newTeam.setName("TeamB");
            em.persist(newTeam);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member findMember = em.find(Member.class , member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("### findTeam.getName()1 : " + findTeam.getName());

            findMember.setTeam(newTeam);
            findTeam = findMember.getTeam();
            System.out.println("### findTeam.getName()2 : " + findTeam.getName());

            //JPQL
            List<Member> resultList =  em.createQuery(
                    "select m from Member m where m.username like '%member%'", Member.class
            ).getResultList();
            System.out.println(resultList);

            //Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query =  cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "member"));
            List<Member> resultList2 = em.createQuery(cq).getResultList();
            System.out.println(resultList2);

            //NativeQuery
            List<Member> resultList3 = em.createNativeQuery("select * from member",Member.class)
                            .getResultList();
            tx.commit();


        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
