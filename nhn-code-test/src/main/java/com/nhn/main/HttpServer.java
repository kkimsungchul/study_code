package com.nhn.main;


import com.nhn.property.ConfigVO;
import com.nhn.property.JsonPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cybaek on 15. 5. 22..
 * @author 김성철 , 2023.05
 */
public class HttpServer {
    static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private static final int NUM_THREADS = 50;
    private final int port;
    private final ConfigVO config;
    public HttpServer(int port , ConfigVO config){
        this.port = port;
        this.config = config;
    }

    /**
     * 서버 실행
     */
    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            LOGGER.info("Accepting connections on port " + server.getLocalPort());
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable runnable = new RequestProcessor(config , request);
                    pool.submit(runnable);
                } catch (IOException ex) {
                    LOGGER.error("Error accepting connection", ex);
                }
            }
        }catch (IOException ioe){
            LOGGER.error("HttpServer start error ," , ioe);
        }
    }

    public static void main(String[] args) {
        JsonPropertyReader jsonPropertyReader = new JsonPropertyReader();
        ConfigVO config = jsonPropertyReader.readProperty();
        int port = config.getPort();
        LOGGER.info("server starting.., connection port : {}",port);
        HttpServer webserver = new HttpServer(port , config);
        webserver.start();

    }
}