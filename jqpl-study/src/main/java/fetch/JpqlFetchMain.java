package fetch;

import domain.Member;
import domain.MemberType;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpqlFetchMain {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();



        tx.begin();
        try{
            Team tema1 = new Team();
            tema1.setName("teamA");
            em.persist(tema1);

            Team tema2 = new Team();
            tema2.setName("teamB");
            em.persist(tema2);

            Team tema3 = new Team();
            tema3.setName("teamC");
            em.persist(tema3);

            Member member = new Member();
            member.setUsername("회원1");
            member.setAge(8);
            member.setType(MemberType.ADMIN);
            member.setTeam(tema1);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(8);
            member2.setType(MemberType.ADMIN);
            member2.setTeam(tema1);
            em.persist(member2);
            em.flush();

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(8);
            member3.setType(MemberType.ADMIN);
            member3.setTeam(tema2);
            em.persist(member3);
            em.flush();
            em.clear();


            //String query = "select m From Member m";
            //String query = "select m From Member m join fetch m.team";
            //String query = "select distinct t from Team t join fetch t.members";
            //String query = "select t from Team t join t.members m";
            String query = "select t from Team t join fetch t.members m";

            List<Team> result = em.createQuery(query , Team.class)
                    .getResultList();
            for(Team team : result){
                System.out.println("# team = " + team.getName() + " | " + team.getMembers().size());
                for(Member resultMember : team.getMembers()){
                    System.out.println("# member : " + resultMember);
                }
            }
            //엔티티 직접 사용
            String jpql1 = "select m from Member m where m = :member";
            List<Member> resultList1 = em.createQuery(jpql1)
                    .setParameter("member", member)
                    .getResultList();

            //식별자를 직접 전달
            long memberId = 1;
            String jpql2 = "select m from Member m where m.id = :memberId";
            List<Member> resultList2 = em.createQuery(jpql2)
                    .setParameter("memberId", memberId)
                    .getResultList();

            System.out.println("##################");
            Team team1 = em.find(Team.class,1L);
            String jpql3 = "select m from Member m  where m.team = :team";
            List<Member> resultList3 = em.createQuery(jpql3)
                    .setParameter("team", team1)
                    .getResultList();

            String jpql4 = "select m from Member m  where m.team.id = :teamId";
            List<Member> resultList4 = em.createQuery(jpql4)
                    .setParameter("teamId", team1.getId())
                    .getResultList();


            System.out.println("#########");
            em.createNamedQuery("Member.findByUsername" , Member.class)
                            .setParameter("username", "회원1")
                            .getResultList();


            int resultCount = em.createQuery("update Member m set m.age = 20")
                            .executeUpdate();
            System.out.println("member3.getAge() = " + member3.getAge());

            Member findMember = em.find(Member.class , member3.getId());
            System.out.println("member3.getAge() = " +findMember.getAge());

            em.clear();
            Member findMember2 = em.find(Member.class , member3.getId());
            System.out.println("member3.getAge() = " +findMember2.getAge());




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
