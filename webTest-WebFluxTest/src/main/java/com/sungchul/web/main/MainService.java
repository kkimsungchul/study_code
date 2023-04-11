package com.sungchul.web.main;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service("mainService")
public class MainService {



    //https://api.upbit.com/v1/market/all?isDetails=false
    //https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC
    //"https://api.upbit.com/v1/candles/minutes/1?market="+market;
    //"https://api.upbit.com/v1/candles/days?market="+market+"&to=";
    //"https://api.upbit.com/v1/candles/weeks?market="+market;
    //"https://api.upbit.com/v1/candles/months?market="+market;

    public static WebClient webClientService(){
        WebClient client = WebClient.create();
        return client;
    }

    public void getTest(){
        String response = webClientService()
                .get()
                .uri("http://localhost:8082/get/tttt")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("### response : " + response);
    }

    public void postTest(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","김성철");
        map.put("age",33);
        map.put("address","경기도 용인시 처인구 역북동");

        String response = webClientService()
                .post()
                .uri("http://localhost:8082/post")
                .bodyValue(map)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("### response : " + response);

    }

    public void putTest(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("직업" , "개발자");
        map.put("경력","만5년");
        String response = webClientService()
                .put()
                .uri("http://localhost:8082/put")
                .bodyValue(map)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("### response : " + response);

    }

    public void deleteTest(){
        String response = webClientService()
                .delete()
                .uri("http://localhost:8082/delete/dddd")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("### response : " + response);

    }



    public String webClientBaseTest(){
        String response  =  webClientService()
                .get()
                .uri("https://api.upbit.com/v1/market/all?isDetails=false")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    public List<HashMap<String,Object>> webClientBaseTestList(){
        WebClient client = WebClient.create();

        List<HashMap<String,Object>> response  =  client
                .get()
                .uri("https://api.upbit.com/v1/market/all?isDetails=false")
                .retrieve()
                .bodyToMono(ArrayList.class)
                .block();
        return response;
    }

}
