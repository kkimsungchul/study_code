package com.sungchul.web.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainService mainService;


    @GetMapping("/get")
    public void getTest(){
        mainService.getTest();
    }

    @GetMapping("/post")
    public void postTest(){
        mainService.postTest();
    }

    @GetMapping("/put")
    public void putTest(){
        mainService.putTest();
    }

    @GetMapping("/delete")
    public void deleteTest(){
        mainService.deleteTest();
    }





    @GetMapping(value="/")
    public ResponseEntity<HashMap<String,Object>> main(){

        HashMap<String,Object> map = new HashMap<>();
        String result = mainService.webClientBaseTest();
        map.put("result" , result);


        ResponseEntity<HashMap<String,Object>> responseEntity = new ResponseEntity<HashMap<String,Object>>(map, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value="/get/list")
    public ResponseEntity<HashMap<String,Object>> getList(){

        HashMap<String,Object> map = new HashMap<>();
        List<HashMap<String,Object>> result = mainService.webClientBaseTestList();
        map.put("result" , result);


        ResponseEntity<HashMap<String,Object>> responseEntity = new ResponseEntity<HashMap<String,Object>>(map, HttpStatus.OK);
        return responseEntity;
    }
}
