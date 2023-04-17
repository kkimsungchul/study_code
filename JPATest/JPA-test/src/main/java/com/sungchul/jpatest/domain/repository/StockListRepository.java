package com.sungchul.jpatest.domain.repository;

import com.sungchul.jpatest.domain.entity.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StockListRepository extends JpaRepository<StockList,Integer> {

    //전체 목록
    List<StockList> findAll();

    // 단순 조건
    public StockList findByStockName(String StockName); // where StockName = ?
    public List<StockList> findByStockLongCode(String StockName); // where StockName = ?


    //조건 여러개
    public List<StockList> findBySeqBetweenAndStockCodeContaining(int startSeq, int endSeq, String stockCode); // where StockCategoryCode between ? and ? and StockName like %?%
    
    // not
    public List<StockList> findByStockNameNot(String StockName); // where StockName <> ?

    // and
    public List<StockList> findByStockNameAndStockCategoryCode(String StockName, int StockCategoryCode); // where StockName = ? and StockCategoryCode = ?

    // or
    public List<StockList> findByStockNameOrStockCategoryCode(String StockName, int StockCategoryCode); // where StockName = ? or StockCategoryCode = ?

    // between
    public List<StockList> findByStockCategoryCodeBetween(int startStockCategoryCode, int endStockCategoryCode); // where StockCategoryCode between ? and ?

    // 부등호
    public List<StockList> findByStockCategoryCodeLessThan(int StockCategoryCode); // where StockCategoryCode < ?
    public List<StockList> findByStockCategoryCodeLessThanEqual(int StockCategoryCode); // where StockCategoryCode <= ?
    public List<StockList> findByStockCategoryCodeGreaterThan(int StockCategoryCode); // where StockCategoryCode > ?

    // null
    public List<StockList> findByStockCategoryCodeIsNull(); // where StockCategoryCode is null
    public List<StockList> findByStockCategoryCodeIsNotNull(); // where StockCategoryCode is not null

    // like
    public List<StockList> findByStockNameStartingWith(String StockName); // where StockName like ?%
    public List<StockList> findByStockNameEndingWith(String StockName); // where StockName like %?
    public List<StockList> findByStockNameContaining(String StockName); // where StockName like %?%

    // in
    public List<StockList> findByStockCodeIn(Collection<String> stockCode); // where StockList_id in (?, ?, ? ...)
    public List<StockList> findByStockCodeNotIn(Collection<String> stockCode); // where StockList_id not in (?, ?, ? ...)

    // order by
    public List<StockList> findAllByOrderByStockCode(); // order by StockList_id, "AllBy" 주의
    public List<StockList> findByStockNameContainingOrderByStockCategoryCode(String StockName); // where StockName like %?% order by StockCategoryCode
    public List<StockList> findByStockCodeOrderByStockCodeDesc(String stockCode); // where id = ? order by id desc

}
