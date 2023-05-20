package com.nhn.main;

import com.nhn.property.ConfigVO;
import com.nhn.property.HostInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


/**
 * WAS 기능을 구현한 클래스, 과제 진행에 사용해도 된다고 전달받은 예제코드를 수정하여 사용함
 * servlet service 로 전달하기전 데이터 정제 및 설정파일 읽어오는 작업을 함
 * @author 김성철
 */
public class RequestProcessor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);
    private String rootDirectory;
    private String indexFileName = "index.html";
    private Socket connection;
    private HostInfoVO hostInfoVO;



    /**
     * RequestProcessor 생성자
     * 접속하는 host에 따른 root 디렉토리 설정
     * 별다른 설정이 없으면 ahost로 접속
     * @param configVO 설정 정보
     * @param connection 서버 소켓 객체
     */
    public RequestProcessor(ConfigVO configVO ,  Socket connection) {
        LOGGER.info("client connect host  " + connection.getInetAddress().getHostName());
        this.hostInfoVO =  configVO.getHost().get(0);
        for(HostInfoVO hostInfo : configVO.getHost()){
            if(hostInfo.getHostName().equalsIgnoreCase(connection.getInetAddress().getHostName().split("\\.")[0])){
                this.hostInfoVO = hostInfo;
            }
        }
        String rootDirectory = hostInfoVO.getHomeDirectory();
        this.rootDirectory = rootDirectory;
        this.connection = connection;
    }



    /**
     * RequestProcessor 스레드 시작
     * inputStream 을 사용하여 파싱한 URL 정보와
     * connection 객체에서의 outputStream을 servletMapping 메소드에 전달
     */
    @Override
    public void run() {
        // for security checks
        try {
            OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
            Writer out = new OutputStreamWriter(raw,"UTF-8");
            Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "UTF-8");
            StringBuilder requestLine = new StringBuilder();
            while (true) {
                int c = in.read();
                if (c == '\r' || c == '\n')
                    break;
                requestLine.append((char) c);
            }
            String get = requestLine.toString();
            LOGGER.info("run - connection.getRemoteSocketAddress() " + connection.getRemoteSocketAddress() + " " + get);

            ServletService servletService = new ServletService(rootDirectory , connection);
            servletService.servletMapping(get , out);

        } catch (Exception ex) {
            LOGGER.error("Error talking to " + connection.getRemoteSocketAddress(), ex);
        } finally {
            try {
                connection.close();
            } catch (IOException ex) {
                LOGGER.error("Connection Close Error {}",ex);
            }
        }
    }





}