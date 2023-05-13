package com.kakaopay.assignment.stock.scheduler;


import com.kakaopay.assignment.stock.main.StockService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ChangeDateScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ChangeDateScheduler.class);

    StockService stockService;
    /**
     * 매일 0시에 실행하는 스케줄러,
     * 날자가 변경될 경우 어제의 마지막 주식가격을 오늘의 최초 시작가로 insert
     * */
    @Scheduled(cron = "0 0 0 * * *")
    public void toDayDataInsert(){
        logger.info("## ChangeDateScheduler start");
        stockService.toDayDataInsert();
        logger.info("## ChangeDateScheduler end");

    }
}
