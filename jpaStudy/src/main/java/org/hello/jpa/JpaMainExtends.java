package org.hello.jpa;

import org.hello.jpa.member.domain.Item;
import org.hello.jpa.member.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainExtends {
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

            Movie movie = new Movie();
            movie.setDirector("AAAA");
            movie.setActor("BBB");
            movie.setName("어벤져스");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

//            Movie movie1 = em.find(Movie.class,movie.getId());
//            System.out.println(movie1);

            Item item = em.find(Item.class,movie.getId());
            System.out.println(item);

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