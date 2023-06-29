package when;

import domain.Member;
import domain.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlCaseMain {
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
            member2.setUsername(null);
            member2.setAge(8);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);
            em.flush();

            String query = "select " +
                    "case when m.age<=10 then '학생요금' " +
                    "when m.age>=60 then '경로요금' " +
                    "else '일반요금' " +
                    "end " +
                    "from Member m";
            List<String> result = em.createQuery(query , String.class)
                    .getResultList();
            for(String s : result){
                System.out.println("S : " + s);
            }

            String query1 = "select coalesce(m.username , '이름 없는 회원') as username from Member m ";
            List<String> result1 = em.createQuery(query1 , String.class)
                    .getResultList();
            for(String s : result1){
                System.out.println("S : " + s);
            }


            String query2 = "select nullif(m.username,'관리자') from Member m ";
            List<String> result2 = em.createQuery(query2 , String.class)
                    .getResultList();
            for(String s : result2){
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
