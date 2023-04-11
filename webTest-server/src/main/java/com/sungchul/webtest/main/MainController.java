package com.sungchul.webtest.main;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MainController {


    @PostMapping("/post")
    public ResponseEntity postTest(@RequestBody HashMap<String,Object> dataMap){
        System.out.println(dataMap);
        HashMap<String,Object> map = new HashMap<>();
        map.put("message" , "POST success");
        map.put("data" , dataMap);


        return new ResponseEntity(map, HttpStatus.OK);

    }


    @GetMapping("/get/{data}")
    public ResponseEntity getTest(@PathVariable("data") String data){
        System.out.println(data);
        return new ResponseEntity("GET SUCCESS", HttpStatus.OK);
    }


    @PutMapping("/put")
    public ResponseEntity putTest(@RequestBody HashMap<String,Object> dataMap){
        System.out.println(dataMap);
        return new ResponseEntity("PUT SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{data}")
    public ResponseEntity deleteTest(@PathVariable("data") String data){
        System.out.println(data);
        return new ResponseEntity("DELETE SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/error")
    public ResponseEntity errorTest(){
        return new ResponseEntity("DELETE SUCCESS", HttpStatus.BAD_GATEWAY);
    }


}
