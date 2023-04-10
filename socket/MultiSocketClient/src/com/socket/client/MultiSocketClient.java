package com.socket.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class MultiSocketClient {

    public static void main(String[] args) {
//        MultiSocketClient multiSocketClient = new MultiSocketClient();
//        multiSocketClient.start();
        HashMap<String,Object> map = new HashMap<>();
        map.put("String","aaaaa");
        System.out.println(map);

        
    }

    public void start(){
        Socket socket = null;
        BufferedReader in = null;
        try{
            socket = new Socket("localhost",8000);
            System.out.println("Server connection success");

            String name = "user " + (int)((Math.random())*100);
            Thread sendThread = new SendThread(socket,name);
            sendThread.start();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(in!=null){
                String inputMessage = in.readLine();
                if(("[" + name + "] chat room out").equalsIgnoreCase(inputMessage)){
                    break;
                }
                System.out.println("From : " + inputMessage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


class SendThread extends Thread {
    Socket socket = null;
    String name;

    Scanner scanner = new Scanner(System.in);

    public SendThread(Socket socket , String name){
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run(){
        try{


            //최초 1회 사용자의 이름을 서버에 전송
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(name);
            out.flush();

            while(true){

                String outputMessage = scanner.nextLine();
                out.println(outputMessage);
                out.flush();
                if("exit".equalsIgnoreCase(outputMessage)){
                    break;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}