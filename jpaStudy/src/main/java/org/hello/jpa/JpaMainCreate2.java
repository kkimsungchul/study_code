package org.hello.jpa;

import org.hello.jpa.member.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainCreate2 {
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
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("member100");
//            em.persist(member);//1차 캐시에 저장

            //DB에있는 데이터가 아닌 1차 캐시에 있는 데이터를 가져옴
//            Member findMember1 = em.find(Member.class,100L);
//            Member findMember2 = em.find(Member.class,100L);
//            System.out.println(findMember1==findMember2);


            em.flush();
            System.out.println("### commit start ###");
            tx.commit();//commit 하는 시점에 쓰기 지연 SQL 저장소에 있는  INSERT 쿼리가 실행됨
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