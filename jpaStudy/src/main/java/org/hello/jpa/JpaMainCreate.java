package org.hello.jpa;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainCreate {
    public static void main(String[] args) {

        //EntityManagerFactory는 클래스 로딩시점에 하나만 만들어야함
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        //DB커넥션을 얻고 쿼리를 날릴때 마다 생성해줘야함
        EntityManager em = emf.createEntityManager();
        //jpa는 트랜잭션안에서 작업해야함 쿼리문 실행할때 DB커넥션을 받아와야함
        EntityTransaction tx = em.getTransaction();
        //트랜잭션 실행
        tx.begin();
        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            //사용을 다하면 닫아줘야함
            em.close();
        }
        emf.close();
    }
}