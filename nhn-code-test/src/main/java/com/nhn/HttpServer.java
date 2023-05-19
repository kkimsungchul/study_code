package com.nhn;


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
 */
public class HttpServer {
    static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    private static final int NUM_THREADS = 50;
    private final int port;
    private final ConfigVO config;
    public HttpServer(int port , ConfigVO config) throws IOException {
        this.port = port;
        this.config = config;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            LOGGER.info("Accepting connections on port " + server.getLocalPort());
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(config , request);
                    pool.submit(r);
                } catch (IOException ex) {
                    LOGGER.error("Error accepting connection", ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        JsonPropertyReader jsonPropertyReader = new JsonPropertyReader();
        ConfigVO config = jsonPropertyReader.readProperty();
        int port = config.getPort();
        LOGGER.info("server start, connection port : {}",port);
        try {
            HttpServer webserver = new HttpServer(port , config);
            webserver.start();
        } catch (IOException ex) {
            LOGGER.error("Server could not start", ex);
        }
    }
}