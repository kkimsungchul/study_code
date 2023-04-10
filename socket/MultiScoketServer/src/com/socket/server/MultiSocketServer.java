package com.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiSocketServer {

    public static void main(String[] args) {

        MultiSocketServer multiSocketServer = new MultiSocketServer();
        multiSocketServer.start();
    }

    //서버 소켓 생성
    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //서버의 포트번호 설정
            serverSocket = new ServerSocket(8000);
            while (true) {
                System.out.println("Client connection waiting");
                socket = serverSocket.accept();


                ReceiveThread receiveThread = new ReceiveThread(socket);
                receiveThread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try{
                    serverSocket.close();
                    System.out.println("Server stop");
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Server socket error");
                }


            }
        }

    }
}


class ReceiveThread extends Thread {

    static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

    Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;

    public ReceiveThread(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            list.add(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String name = "";

        try {
            //사용자 첫 입장시에만 띄움
            name = in.readLine();
            System.out.println("[" + name + "] chat room in");
            sendAll("[" + name + "] chat room in");
            //반복
            while (true) {
                String inputMessage = in.readLine();
                if ("exit".equalsIgnoreCase(inputMessage)) {
                    break;
                }
                sendAll(name + ">> " + inputMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sendAll("[" + name + "] chat room out");
            list.remove(out);
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void sendAll(String message) {

        for (PrintWriter out : list) {
            out.println(message);
            out.flush();
        }
    }


}