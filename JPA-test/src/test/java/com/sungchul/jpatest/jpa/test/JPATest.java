package com.sungchul.jpatest.jpa.test;

import com.sungchul.jpatest.domain.repository.AdminUserRepository;
import com.sungchul.jpatest.domain.repository.ParsingDataRepository;
import com.sungchul.jpatest.domain.repository.StockListRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


//@WebAppConfiguration
@SpringBootTest
@DisplayName("JPA SELECT 테스트")
public class JPATest {

    @Autowired
    StockListRepository stockListRepository;

    @Autowired
    AdminUserRepository adminUserRepository;
    
//    @Autowired
//    ParsingDataRepository parsingDataRepository;

    @Order(1)
    @Test
    @DisplayName("stock_list 테이블 데이터 가져오기")
    public void getStockListAll(){
        System.out.println(stockListRepository.findAll());
    }

    @Order(2)
    @Test
    @DisplayName("admin_user 테이블 데이터 가져오기")
    public void getAdminUserAll(){
        System.out.println(adminUserRepository.findAll().toString());

    }

    @Order(3)
    @Test
    @DisplayName("admin_user 테이블 name이 일치하는 데이터 가져오기")
    public void findAllByName(){
        System.out.println(adminUserRepository.findAllByName("성철이"));

    }

    @Order(4)
    @Test
    @DisplayName("admin_user 테이블 UserId에 특정값이 포함된 데이터 가져오기 (like)")
    public void findAllByUserIdContaining(){
        System.out.println(adminUserRepository.findAllByUserIdContaining(1));
    }

    @Order(5)
    @Test
    @DisplayName("stock_list 테이블에서 stockCode 에 특장값이 포함된 데이터 가져오기 (in)")
    public void findByStockCodeIn(){
        ArrayList<String> stockCodeList = new ArrayList<>();
        stockCodeList.add("001460");
        stockCodeList.add("058820");
        stockCodeList.add("009520");
        stockCodeList.add("003550");
        System.out.println(stockListRepository.findByStockCodeIn(stockCodeList));

    }

    @Order(6)
    @Test
    @DisplayName("stock_list 테이블에서 stockName 이 일치하는 데이터 가져오기")
    public void findByStockName(){
        System.out.println(stockListRepository.findByStockName("삼성전자"));
    }

//    @Order(7)
//    @Test
//    @DisplayName("parsing_data 에 있는 모든 데이터 가져오기")
//    public void parsingDataFindAll(){
//        System.out.println(parsingDataRepository.findTop3());
//    }

    @Order(7)
    @Test
    @DisplayName("admin_user 테이블 UserId로 정렬하여 10개 가져오기")
    public void findTop10ByOrderByUserId(){
        System.out.println(adminUserRepository.findTop10ByOrderByUserId());
    }


}
