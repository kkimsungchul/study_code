package org.hello.jpa.Embed;

import org.hello.jpa.member.domain.Member;
import org.hello.jpa.member.domain.Team;
import org.hello.jpa.member.domain.embedded.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainEmbed {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em =  emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            Address address = new Address("용인","명지로","15999");
            Member member = new Member();
            member.setTeam(team);
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);
            //address 의 값 변경을 하고 싶을때 새로운 객체를 생성해서 변경
            Address newAddress = new Address(address.getCity(),address.getStreet(),"14888");
            member.setHomeAddress(newAddress);
            em.persist(member);
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
