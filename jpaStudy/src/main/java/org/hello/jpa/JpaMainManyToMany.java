package org.hello.jpa;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainManyToMany {
    public static void main(String[] args) {

        //EntityManagerFactory는 클래스 로딩시점에 하나만 만들어야함
        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");
        //DB커넥션을 얻고 쿼리를 날릴때 마다 생성해줘야함
        EntityManager em = emf.createEntityManager();
        //jpa는 트랜잭션안에서 작업해야함 쿼리문 실행할때 DB커넥션을 받아와야함
        EntityTransaction tx = em.getTransaction();
        //트랜잭션 실행
        tx.begin();
        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);


//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class,team.getId());
            //실제 사용하는 시점에서 쿼리를 한번 더 날림
            List<Member> members = findTeam.getMembers();

            for(Member m : members){
                System.out.println("## m.getUsername() : " + m.getUsername());
            }




//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class , member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("### findTeam.getName()1 : " + findTeam.getName());
//            //System.out.println("### findTeam.getName()1 : " + findMember.getTeam().getMembers());
//            List<Member> memberList = findMember.getTeam().getMembers();
//
//            for(Member m : memberList){
//                System.out.println("## m : " + m.getUsername());
//            }

//            findMember.setTeam(newTeam);
//            findTeam = findMember.getTeam();
//            System.out.println("### findTeam.getName()2 : " + findTeam.getName());



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