package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Test t = new Test();
        ForkJoinPool customThreadPool = new ForkJoinPool(4); // 원하는 스레드 수로 조정
        CompletableFuture<String> futureFirst  =t.CompletableFutureTestMethod("first",customThreadPool);
        CompletableFuture<String> futureSecond =t.CompletableFutureTestMethod("second",customThreadPool);
        CompletableFuture<String> futureThird =t.CompletableFutureTestMethod("third",customThreadPool);
        CompletableFuture.allOf(futureFirst, futureSecond, futureThird).join();
        System.out.println(futureFirst.get());
        System.out.println(futureSecond.get());
        System.out.println(futureThird.get());

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("메서드 실행 시간: " + elapsedTime + " 밀리초");
    }


}

class Test {
    public CompletableFuture<String> CompletableFutureTestMethod(String message,ForkJoinPool customThreadPool) {

        return CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(message + i);
            }
            return "success";
        }, customThreadPool);
    }
}