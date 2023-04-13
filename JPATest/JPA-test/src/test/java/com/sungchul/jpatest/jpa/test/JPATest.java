package com.sungchul.jpatest.jpa.test;

import com.sungchul.jpatest.domain.repository.AdminUserRepository;
import com.sungchul.jpatest.domain.repository.StockListRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//@WebAppConfiguration
@SpringBootTest
@DisplayName("JPA SELECT 테스트")
public class JPATest {

    @Autowired
    StockListRepository stockListRepository;

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    @DisplayName("stock_list 테이블 데이터 가져오기")
    public void getStockListAll(){
        System.out.println(stockListRepository.findAll());
    }

    @Test
    @DisplayName("admin_user 테이블 데이터 가져오기")
    public void getAdminUserAll(){
        System.out.println(adminUserRepository.findAll().toString());

    }

    @Test
    @DisplayName("admin_user 테이블 name이 일치하는 값 가져오기")
    public void getAdminUserByName(){
        System.out.println(adminUserRepository.findAllByName("성철이"));

    }

    @Test
    @DisplayName("admin_user 테이블 UserId가 일치하는 값 가져오기")
    public void getAdminUserByUserId(){
        System.out.println(adminUserRepository.findAllByUserIdContaining(1));

    }




}
