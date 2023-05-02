package com.jpabook;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaShop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Order order = em.find(Order.class , 1L);
            Member findMEmber = order.getMember();

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