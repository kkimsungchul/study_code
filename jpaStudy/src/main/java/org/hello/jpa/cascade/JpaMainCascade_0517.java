package org.hello.jpa.cascade;

import org.hibernate.annotations.Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainCascade_0517 {

    public static void main(String[]args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try{
            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();
            parent.addChild(child1);
            parent.addChild(child2);

            entityManager.persist(parent);

            child1.setName("김성철");

            entityManager.flush();
            entityManager.clear();
            Parent findParent = entityManager.find(Parent.class , parent.getId());
            findParent.getChildList().remove(0);

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
