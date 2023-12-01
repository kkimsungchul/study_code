package com.sungchul.completablefuturetestserver.server.main;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @GetMapping("/")
    public String normalTest(){
        long startTime = System.currentTimeMillis();

        String first = serverService.normalAPITest("first");
        String second = serverService.normalAPITest("second");
        String third = serverService.normalAPITest("third");

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("yourMethod execution time: " + executionTime + " milliseconds");

        return "server connection success ! Normal execution time : " + executionTime + " milliseconds";
    }

    @GetMapping("/future")
    public String futureTest() throws Exception{
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> completableFutureFirst = serverService.completableFutureAPITest("first");
        CompletableFuture<String> completableFutureSecond = serverService.completableFutureAPITest("second");
        CompletableFuture<String> completableFutureThird = serverService.completableFutureAPITest("third");
//        CompletableFuture.allOf(completableFutureFirst,completableFutureSecond,completableFutureThird).join();
        System.out.println(completableFutureSecond.get());
        System.out.println(completableFutureFirst.get());
        System.out.println(completableFutureThird.get());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("yourMethod execution time: " + executionTime + " milliseconds");

        return "server connection success ! CompletableFuture execution time : " + executionTime + " milliseconds";
    }

}
