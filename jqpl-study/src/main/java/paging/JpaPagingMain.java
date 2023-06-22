package paging;

import domain.Member;
import domain.MemberDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaPagingMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            for(int i=0;i<50;i++){
                Member member = new Member();
                member.setUsername("member1"+i);
                member.setAge(20+i);
                em.persist(member);
            }
            em.flush();
            em.clear();
            List<Member>resultList =  em.createQuery("select m from Member m order by m.age asc", Member.class)
                            .setFirstResult(0)//시작값
                            .setMaxResults(10)//갯수
                            .getResultList();

            for(Member member1 : resultList){
                System.out.println("#####");
                System.out.println(member1.getUsername());
                System.out.println(member1.getAge());
            }




            tx.commit();
        }catch (Exception e){
            tx.rollback();

            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
