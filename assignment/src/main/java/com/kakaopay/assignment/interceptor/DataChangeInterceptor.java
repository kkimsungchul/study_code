package com.kakaopay.assignment.interceptor;

import com.kakaopay.assignment.stock.main.DataChangeVO;
import com.kakaopay.assignment.stock.main.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * API 요청시 DB에 저장되어 있는 데이터 갱신
 * */
public class DataChangeInterceptor implements HandlerInterceptor {

    @Autowired
    StockService stockService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        stockService.dataChange(DataChangeVO.builder().type("all").build());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
