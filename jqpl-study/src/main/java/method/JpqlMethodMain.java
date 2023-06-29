package method;

import domain.Member;
import domain.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlMethodMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("김성철");
            member.setAge(8);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("양혜은");
            member2.setAge(8);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);
            em.flush();

            String query = "select group_concat(m.username) From Member m";

            List<String> result = em.createQuery(query,String.class)
                    .getResultList();
            for(String a : result){
                System.out.println(a);
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
