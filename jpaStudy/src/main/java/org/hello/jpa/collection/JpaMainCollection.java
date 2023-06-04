package org.hello.jpa.collection;

import org.hello.jpa.member.domain.embedded.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainCollection {
    public static void main(String[]args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
//            MemberCollection memberCollection = new MemberCollection();
//            memberCollection.setUsername("멤버컬렉션테스트");
//            memberCollection.setHomeAddress(new Address("용인시" , "명지로" , "15999"));
//            memberCollection.getFavoriteFoods().add("치킨");
//            memberCollection.getFavoriteFoods().add("피자");
//            memberCollection.getFavoriteFoods().add("탕수육");
//            memberCollection.getAddressHistory().add(new Address("용인시","김량장동","10000"));
//            memberCollection.getAddressHistory().add(new Address("광주시","신현리","20000"));
//            em.persist(memberCollection);
//
//            em.flush();
//            em.clear();
//
//            MemberCollection findMember = em.find(MemberCollection.class , memberCollection.getId());
//            //아래와 같이 수정하면 안됨, 사이드 이펙트 발생
//            //findMember.getHomeAddress().setCity("서울특별시");
//            //새로운 인스턴스 생성 후 완전히 교체해줘야 함
//            Address oldAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("서울특별시",oldAddress.getStreet(),oldAddress.getZipcode()));
//
//            //변경하려는 값을 지우고 새로 넣어야 함
//            //컬렉션의 값만 변경해도 JPA가 알아서 값을 변경해줌 (영속성 전이가 되는것처럼 작동함)
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("비빔밥");
//
//            //equals 비교로 해당값을 똑같이 가지고 있는 객체를 제거
//            findMember.getAddressHistory().remove(new Address("용인시","김량장동","10000"));
//            findMember.getAddressHistory().add(new Address("용인시","역북동","15900"));

            MemberCollection2 memberCollection = new MemberCollection2();
            memberCollection.setUsername("멤버컬렉션테스트");
            memberCollection.setHomeAddress(new Address("용인시" , "명지로" , "15999"));
            memberCollection.getFavoriteFoods().add("치킨");
            memberCollection.getFavoriteFoods().add("피자");
            memberCollection.getFavoriteFoods().add("탕수육");
            memberCollection.getAddressHistory().add(new AddressEntity("용인시","김량장동","10000"));
            memberCollection.getAddressHistory().add(new AddressEntity("광주시","신현리","20000"));
            em.persist(memberCollection);

            em.flush();
            em.clear();

//            MemberCollection findMember = em.find(MemberCollection.class , memberCollection.getId());
//            //아래와 같이 수정하면 안됨, 사이드 이펙트 발생
//            //findMember.getHomeAddress().setCity("서울특별시");
//            //새로운 인스턴스 생성 후 완전히 교체해줘야 함
//            Address oldAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("서울특별시",oldAddress.getStreet(),oldAddress.getZipcode()));
//
//            //변경하려는 값을 지우고 새로 넣어야 함
//            //컬렉션의 값만 변경해도 JPA가 알아서 값을 변경해줌 (영속성 전이가 되는것처럼 작동함)
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("비빔밥");
//
//            //equals 비교로 해당값을 똑같이 가지고 있는 객체를 제거
//            findMember.getAddressHistory().remove(new Address("용인시","김량장동","10000"));
//            findMember.getAddressHistory().add(new Address("용인시","역북동","15900"));

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
