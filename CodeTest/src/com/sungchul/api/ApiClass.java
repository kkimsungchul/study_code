package com.sungchul.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//Java 로 외부 URL호출하여 데이터 가져오는 클래스
public class ApiClass {

    /**
     * @param strUrl 호출 URL
     * @return return 값
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String get(String strUrl) throws NoSuchAlgorithmException, KeyManagementException
    {
        BufferedReader br = null;
        String returnString="";
        try
        {
            URL url = new URL(strUrl);
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType){
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application/json");

            /*
             * URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다.
             * URL 연결은 입출력에 사용될 수 있다. URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고,
             * 그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.
             */
            con.setDoOutput(false);

            StringBuffer sb = new StringBuffer();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                String line = null;
                while ((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                }
                returnString = sb.toString();
                System.out.println("GET Rest API Url="+strUrl+", responseStr=" + sb.toString());
            }else{
                System.out.println("GET Rest API Url="+strUrl+", responseCode="+con.getResponseCode()+", responseMessage=" + con.getResponseMessage());
            }
        }catch(IOException ioe){
            System.out.println("GET Rest API Url="+strUrl+", exceptionMessage="+ioe.getMessage());
        }finally{
            if(br != null){try{br.close();}catch(IOException e){}}
        }

        return returnString;
    }


    /**
     * @param strUrl - 호출 URL
     * @param jsonMessage - 전달할 데이터
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public void post(String strUrl, String jsonMessage) throws NoSuchAlgorithmException, KeyManagementException{
        OutputStreamWriter wr = null;
        BufferedReader br = null;

        try {
            URL url = new URL(strUrl);


            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType){
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
//            con.setDoInput(true);
            con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
//            con.setUseCaches(false);
//            con.setDefaultUseCaches(false);

            wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonMessage); //json 형식의 message 전달
            wr.flush();

            StringBuffer sb = new StringBuffer();
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                String line = null;
                while((line = br.readLine()) != null){
                    System.out.println("## line : " + line);
                    System.out.println( con.getResponseMessage());
                    sb.append(line).append("\n");
                }

                System.out.println("POST Rest API Url="+strUrl+", responseStr=" + sb.toString());
            }else{
                System.out.println("POST Rest API Url="+strUrl+", responseCode="+con.getResponseCode()+", responseMessage=" + con.getResponseMessage());
            }
        }catch(IOException ioe){
            System.out.println("POST Rest API Url="+strUrl+", exceptionMessage="+ioe.getMessage());
        }finally{
            if(br != null){try{br.close();}catch(IOException e){}}
            if(wr != null){try{wr.close();}catch(IOException e){}}
        }


    }
}
