package com.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[]args){

        BufferedReader in = null;
        PrintWriter out = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);

        try{
            //서버 소켓 생성
            serverSocket = new ServerSocket(8000);
            System.out.println("Server Start , Client connecting waiting");

            //클라이언트 접속 대기
            socket = serverSocket.accept();
            System.out.println(" Client connection success");

            //데이터 송수신을 위한 스트림 생성성
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(true){
                //입력받은 메시지출력
                String inputMessage = in.readLine();
                if("exit".equalsIgnoreCase(inputMessage)){
                    break;
                }

                System.out.println("From Client : " + inputMessage);
                System.out.println("Send Message : ");

                //보낼 메시지 입력
                String outputMessage = scanner.nextLine();
                out.println(outputMessage);
                out.flush();
                if("exit".equalsIgnoreCase(outputMessage)){
                    break;
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{

                //통신 종료
                scanner.close();;
                socket.close();;
                serverSocket.close();
                System.out.println("System shutdown...");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
