package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.order.simplerquery.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOnd(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch){

        //JPQL 사용시 동적 쿼리 시작 
        String jpql = "select o from Order o join o.member m ";
        boolean isFirstCondition = true;
        if(orderSearch.getOrderStatus()!=null){
            if(isFirstCondition){
                jpql += " where ";
                isFirstCondition = false;
            }else{
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            }else{
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);
        if(orderSearch.getOrderStatus() !=null){
            query = query.setParameter("status" , orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name", orderSearch.getMemberName());

        }
        return query.getResultList();
        //JPQL 사용시 동적 쿼리 종료

//  모든 조건이 있는 경우의 쿼리
//        return em.createQuery("select 8 from Order o join o.member m " +
//                        "where o.status = :status " +
//                        "and m.name like :name", Order.class)
//                .setParameter("status" , orderSearch.getOrderStatus())
//                .setParameter("name" , orderSearch.getMemberName())
//                .setMaxResults(1000)
//                .getResultList();

        }

    /**
     * JPA Criteria 사용
     */
    public List<Order> findByCriteria(OrderSearch orderSearch){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> o = query.from(Order.class);
        Join<Object,Object> m = o.join("member" , JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if(orderSearch.getOrderStatus()!=null){
            Predicate status = criteriaBuilder.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            Predicate status = criteriaBuilder.equal(o.get("name"), "%"+ orderSearch.getMemberName() +"%");
            criteria.add(status);
        }

        query.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> orderTypedQuery = em.createQuery(query).setMaxResults(1000);
        return orderTypedQuery.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o"+
                        " join fetch o.member m" +
                        " join fetch o.delivery d" , Order.class
        ).getResultList();
    }

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id ,m.name, o.orderDate , o.status , d.address) " +
                        " from Order o " +
                        " join o.member m " +
                        " join o.delivery d ", OrderSimpleQueryDto.class)
                .getResultList();
    }





    /**
     * Query DSL
     */
//    public List<Order> findAll(OrderSearch orderSearch){
//
//    }
}
