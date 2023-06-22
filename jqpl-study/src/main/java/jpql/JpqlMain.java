package jpql;

import domain.Address;
import domain.Member;
import domain.MemberDTO;
import domain.Team;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class JpqlMain {
    public static void main(String[]args){
        //EntityManagerFactory는 클래스 로딩시점에 하나만 만들어야함
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        //DB커넥션을 얻고 쿼리를 날릴때 마다 생성해줘야함
        EntityManager em = emf.createEntityManager();
        //jpa는 트랜잭션안에서 작업해야함 쿼리문 실행할때 DB커넥션을 받아와야함
        EntityTransaction tx = em.getTransaction();
        //트랜잭션 실행
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("김성철");
            member.setAge(20);
            em.persist(member);
            em.flush();

            List<Member> result = em.createQuery("select m from Member m" , Member.class)
                    .getResultList();
            result.get(0).setAge(30);

            List<Team> result2 = em.createQuery("select m.team from Member m" , Team.class)
                    .getResultList();

            em.createQuery("select o.address from Order o" , Address.class)
                    .getResultList();

            List resultList = em.createQuery("select distinct m.username , m.age from Member m")
                    .getResultList();
            for (Object o : resultList){
                Object[] objectResult = (Object[])o;
                System.out.println(objectResult[0]);
                System.out.println(objectResult[1]);
            }

            List<Object[]> resultList3 = em.createQuery("select distinct m.username , m.age from Member m")
                    .getResultList();
            System.out.println(resultList3.get(0)[0]);
            System.out.println(resultList3.get(0)[1]);

            List<MemberDTO> resultList4 = em.createQuery("select new domain.MemberDTO(m.username , m.age) from Member m" , MemberDTO.class)
                    .getResultList();
            for(MemberDTO memberDTO : resultList4){
                System.out.println(memberDTO.getUsername());
                System.out.println(memberDTO.getAge());
            }


//            TypedQuery<Member> query1 = em.createQuery("select m from Member m ", Member.class);
//            List<Member> resultList = query1.getResultList();
//            System.out.println(resultList);


//            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.id=100", Member.class);
//            try{
//                Member result = query2.getSingleResult();
//                System.out.println(result);
//            }catch (NoResultException e){
//                e.printStackTrace();
//            }


//            Member member3 = em.createQuery("select m from Member m where m.username = :username",Member.class)
//                    .setParameter("username" , "김성철")
//                    .getSingleResult();
//            System.out.println(member3);


            //Srping Data JPA -> Null 반환 or Optional 반환




            tx.commit();
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
