package org.hello.jpa.lazy;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setTeam(team);
            entityManager.persist(member1);


            entityManager.flush();
            entityManager.clear();

            Member findMember1 = entityManager.getReference(Member.class , member1.getId());
            System.out.println(findMember1.getUsername());
            System.out.println(findMember1.getTeam().getClass());
            System.out.println(findMember1.getTeam().getName());

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
