package type;

import domain.Member;
import domain.MemberType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlTypeMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();



        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("김성철");
            member.setAge(20);
            member.setType(MemberType.ADMIN);
            em.persist(member);
            em.flush();

            String query = "select m.username , 'HELLO' , TRUE From Member m where m.type = :userType";
            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType" , MemberType.ADMIN)
                    .getResultList();

            for(Object[] objects : result){
                System.out.println(objects[0]);
                System.out.println(objects[1]);
                System.out.println(objects[2]);
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
