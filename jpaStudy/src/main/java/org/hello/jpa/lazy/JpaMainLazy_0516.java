package org.hello.jpa.lazy;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainLazy_0516 {
    public static void main(String[]args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try{
            Team team = new Team();
            team.setName("TEAM_A");
            entityManager.persist(team);

            Team team2 = new Team();
            team2.setName("TEAM_B");
            entityManager.persist(team2);

            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setTeam(team);
            entityManager.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello1");
            member2.setTeam(team);
            entityManager.persist(member2);

            Member member3 = new Member();
            member3.setUsername("hello1");
            member3.setTeam(team2);
            entityManager.persist(member3);

            Member member4 = new Member();
            member4.setUsername("hello1");
            member4.setTeam(team2);
            entityManager.persist(member4);


            entityManager.flush();
            entityManager.clear();
            //Member findMember1 = entityManager.getReference(Member.class , member1.getId());

            List<Member> memberList =  entityManager.createQuery("select m from Member m join fetch m.team",Member.class).getResultList();



//            System.out.println("##### 1");
//            System.out.println(findMember1.getUsername());
//            System.out.println("##### 2");
//            System.out.println(findMember1.getTeam().getClass());
//            System.out.println("##### 3");
//            System.out.println(findMember1.getTeam().getName());



            entityTransaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
