package com.nhn.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HttpRequest 구현 클래스
 * @author 김성철
 */
public class HttpRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);
    private String requestURL;
    private Map<String, String> parameters;

    public HttpRequest(String requestURL ,String queryString) {
        this.requestURL = requestURL;
        this.parameters = new HashMap<>();
        extractQueryParams(queryString);
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }
    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * query string 으로 들어온 파라메터들을 파싱하여 hashMap에 저장
     * @param queryString 쿼리스트링
     */
    public void extractQueryParams(String queryString){
        if(queryString==null){
            return ;
        }
        String[] paramPairs = queryString.split("&");

        for (String paramPair : paramPairs) {
            // 각 쿼리 파라미터를 '=' 기준으로 key-value로 분리
            String[] keyValue = paramPair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                // URL 디코딩하여 값 설정
                try{
                    value = java.net.URLDecoder.decode(value, "UTF-8");
                }catch (UnsupportedEncodingException e){
                    LOGGER.error("extractQueryParams URL decoding error ",e);
                }
                this.parameters.put(key, value);
            }
        }
    }

    /**
     * HashMap에 저장되어 있는 데이터를 HTML 파일에 한줄씩 출력할수 있도록 문자열로 반환하는 클래스
     * @return allParameter
     */
    public String getAllParameter(){
        Set<Map.Entry<String, String>> entrySet = this.parameters.entrySet();
        StringBuilder allParameter = new StringBuilder();
        allParameter.append("Query String <br>");
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            allParameter.append("Key: ").append(key).append(", Value: ").append(value).append("<br>");
        }
        if(allParameter.toString().equals("Query String <br>")){
            return "";
        }
        return allParameter.toString();
    }
}
