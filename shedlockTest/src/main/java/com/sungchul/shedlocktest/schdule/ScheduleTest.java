package com.sungchul.shedlocktest.schdule;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableSchedulerLock(defaultLockAtMostFor = "PT10S")
@Component
public class ScheduleTest {


    @Scheduled(cron ="0 0/1 * * * *")
    @SchedulerLock(name="ScheduleTestMethod",lockAtMostFor = "10S", lockAtLeastFor = "10S")
    public void ScheduleTestMethod(){
        for(int i=0;i<100000;i++){
            System.out.println("### i : " + i);
        }
    }
}
