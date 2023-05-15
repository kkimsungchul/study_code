package org.hello.jpa.proxy;

import org.hello.jpa.member.domain.Member;
import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMainProxy_0516 {
    public static void main(String[]args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try{
            Member member1 = new Member();
            member1.setUsername("hello1");
            entityManager.persist(member1);

            entityManager.flush();
            entityManager.clear();

            Member findMember1 = entityManager.getReference(Member.class , member1.getId());
//            System.out.println(findMember1.getClass());
//            System.out.println("## isLoaded : " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1));
//            System.out.println(findMember1.getUsername());
//            System.out.println("## isLoaded : " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1));
//            System.out.println(findMember1.getClass());
//            System.out.println(findMember1.getClass().getName());

            Hibernate.initialize(findMember1);

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
