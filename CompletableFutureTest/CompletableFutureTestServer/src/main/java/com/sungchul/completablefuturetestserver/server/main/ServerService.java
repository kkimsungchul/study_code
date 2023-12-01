package com.sungchul.completablefuturetestserver.server.main;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final RestTemplate restTemplate;



    public String normalAPITest(String param){
        return callGetAPI(param);
    }

    public CompletableFuture<String> completableFutureAPITest(String param){
        return CompletableFuture.supplyAsync(() -> {
            return callGetAPI(param);
        });
    }


    public String  callGetAPI(String param){
        String url = "http://localhost:9092/"+param;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
