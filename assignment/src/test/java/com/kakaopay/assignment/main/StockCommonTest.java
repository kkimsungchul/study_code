package com.kakaopay.assignment.main;

import com.kakaopay.assignment.common.CommonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("공통 모듈 테스트")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StockCommonTest {

    @Test
    @DisplayName("퍼센트 계산 테스트")
    public void makePercentTest(){
        int nowPrice =70000;
        int initPrice = 100000;
        String percent = CommonUtil.makePercent(nowPrice,initPrice);
        assertThat(percent).isEqualTo("-30.00%");
    }

    @Test
    @DisplayName("종목 등락률 색상 테스트")
    public void makeColorTest(){
        int nowPrice =70000;
        int initPrice = 100000;
        String color = CommonUtil.makeColor(nowPrice,initPrice);
        assertThat(color).isEqualTo("blue");

        nowPrice =100000;
        initPrice = 80000;
        color = CommonUtil.makeColor(nowPrice,initPrice);
        assertThat(color).isEqualTo("red");

        nowPrice =90000;
        initPrice = 90000;
        color = CommonUtil.makeColor(nowPrice,initPrice);
        assertThat(color).isEqualTo("gray");

    }

    @Test
    @DisplayName("페이징 테스트 - limit null")
    public void makePagingByPageTest(){
        assertThat(CommonUtil.makePaging("0",null).getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("1",null).getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("2",null).getPageNum()).isEqualTo(20);
        assertThat(CommonUtil.makePaging("3",null).getPageNum()).isEqualTo(40);
        assertThat(CommonUtil.makePaging("4",null).getPageNum()).isEqualTo(60);
        assertThat(CommonUtil.makePaging("5",null).getPageNum()).isEqualTo(80);
        assertThat(CommonUtil.makePaging("6",null).getPageNum()).isEqualTo(80);
        assertThat(CommonUtil.makePaging("1000000",null).getPageNum()).isEqualTo(80);
        assertThat(CommonUtil.makePaging("-5",null).getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("adkfjahsdkjfhaksdf",null).getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("99999999999999999999999999999999999999999",null).getPageNum()).isEqualTo(0);
    }

    @Test
    @DisplayName("페이징 테스트 - limit Not null")
    public void makePagingByPageAndLimitTest(){
        assertThat(CommonUtil.makePaging("0","50").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("1","50").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("2","50").getPageNum()).isEqualTo(50);
        assertThat(CommonUtil.makePaging("3","50").getPageNum()).isEqualTo(50);

        assertThat(CommonUtil.makePaging("0","10").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("1","10").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("11","10").getPageNum()).isEqualTo(90);
        assertThat(CommonUtil.makePaging("100","10").getPageNum()).isEqualTo(90);

        assertThat(CommonUtil.makePaging("1000000","100").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("-5","50").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("adkfjahsdkjfhaksdf","asdfasdf").getPageNum()).isEqualTo(0);
        assertThat(CommonUtil.makePaging("99999999999999999999999999999999999999999","20").getPageNum()).isEqualTo(0);

        assertThat(CommonUtil.makePaging("100","17").getPageNum()).isEqualTo(85);
    }

}
