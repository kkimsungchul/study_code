package com.sungchul.jpatest.domain.repository;

import com.sungchul.jpatest.domain.entity.ParsingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParsingDataRepository extends JpaRepository<ParsingData,String> {

    List<ParsingData> findTop33ByOrderBySeq();


}
