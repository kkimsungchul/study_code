package com.sungchul.reflection;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReflectionMain {
    public static void main(String[]args){
        ReflectionTestVO reflectionTestVO = new ReflectionTestVO();
        
        try{
            //server.info 파일을 읽어옴
            BufferedReader br = new BufferedReader( new FileReader("C:\\IntellijProject\\CodeTest\\src\\com\\sungchul\\reflection\\Server.info"));
            String line = null;
            String key="";

            while((line = br.readLine()) != null){
                //시작문자열이 # 인경우 필드명
                if(line.startsWith("#")){
                    key = line.replaceAll("#","");
                }else{

                    //해당 값들은 ,로 구분되어 있어서 split 메소드를 사용하기위하여 if를 사용
                    if(key.equals("serverMemory")){
                        String temp[] = line.split(",");
                        reflectionTestVO.getClass()
                                .getMethod(key+"Total",String.class)
                                .invoke(reflectionTestVO,temp[0]);

                        reflectionTestVO.getClass()
                                .getMethod(key+"Used",String.class)
                                .invoke(reflectionTestVO,temp[1]);

                        reflectionTestVO.getClass()
                                .getMethod(key+"Free",String.class)
                                .invoke(reflectionTestVO,temp[2]);
                    }else{
                        //reflectionTestVO 클래스에서 메소드명이 key안에 있는 값으로 되어 있는 메소드를가져와서 실행함
                        reflectionTestVO.getClass()
                                .getMethod(key,String.class)
                                .invoke(reflectionTestVO,line);
                    }
                }
            }
            for(int i=0;i<reflectionTestVO.getServerDisk().size();i++){

                String temp[] = reflectionTestVO.getServerDisk().get(i).split(",");
                reflectionTestVO.serverDiskPart(temp[0]);
                reflectionTestVO.serverDiskTotal(temp[1]);
                reflectionTestVO.serverDiskUsed(temp[2]);
                reflectionTestVO.serverDiskFree(temp[3]);
                //DB 인서트문 실행 대신 출력으로 변경
                System.out.println(reflectionTestVO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}