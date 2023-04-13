package com.sungchul.jpatest.domain.repository;

import com.sungchul.jpatest.domain.entity.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockListRepository extends JpaRepository<StockList,Integer> {

    List<StockList> findAll();

}
