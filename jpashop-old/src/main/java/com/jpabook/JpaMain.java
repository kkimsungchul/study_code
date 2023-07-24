package com.jpabook;

import com.jpabook.jpashop.domain.Book;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;

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

            Order order = new Order();
            order.addOrderItem(new OrderItem());

            Book book = new Book();
            book.setName("JPA");
            book.setName("저자");
            em.persist(book);
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