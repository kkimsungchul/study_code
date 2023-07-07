package jparoute;

import domain.Member;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlRouteMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();



        tx.begin();
        try{
            Team tema1 = new Team();
            tema1.setName("teamA");

            Member member = new Member();
            member.setUsername("김성철");
            member.setAge(8);
            member.setType(MemberType.ADMIN);
            member.setTeam(tema1);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername(null);
            member2.setAge(8);
            member2.setType(MemberType.ADMIN);
            member2.setTeam(tema1);
            em.persist(member2);
            em.flush();



            String query = "select m.username From Team t join t.members m";;
            List<String> result = em.createQuery(query , String.class)
                    .getResultList();
            for(String s : result){
                System.out.println("S : " + s);
            }

            tx.commit();
        }catch (Exception e){

            tx.rollback();

        }finally {
            em.close();
        }

        emf.close();



    }
}
