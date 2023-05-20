package com.nhn.http;

import com.nhn.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * HttpResponse 구현 클래스
 * @author 김성철
 */
public class HttpResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponse.class);
    private StringBuilder content;
    private Writer writer;
    public HttpResponse(Writer writer) {
        this.content = new StringBuilder();
        this.writer = writer;
    }

    public Writer getWriter() {
        return writer;
    }

    public String getContent() {
        return content.toString();
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }


    /**
     * 전달 받은 데이터를 화면에 출력
     * @param responseCode 쿼리스트링
     * @param contentType 컨텐츠 타입
     * @param body 화면에 출력할 데이터
     */
    public void send(String responseCode, String contentType, String body){
        byte[] responseBody = body.getBytes(StandardCharsets.UTF_8);
        int contentLength = responseBody.length;
        try {
            this.writer.write(responseCode + "\r\n");
            this.writer.write("Date: " + DateUtil.getTime("yyyy-MM-dd HH:mm:ss") + "\r\n");
            this.writer.write("Server: JHTTP 2.0\r\n");
            this.writer.write("Content-length: " + contentLength + "\r\n");
            this.writer.write("Content-type: " + contentType + "\r\n\r\n");
            this.writer.write(body);
            this.writer.flush();

        }catch (IOException ioe){
            LOGGER.error("send Error  ", ioe );
        }finally {
            try{
                this.writer.close();
            }catch (IOException ioe){
                LOGGER.error("writer close Error  ", ioe );
            }

        }
    }
}