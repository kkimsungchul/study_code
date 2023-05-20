# NHN 채용 사전과제 전형_Java
## 목차
- [유의사항](#유의사항)
- [개발 환경](#개발-환경)
- [빌드 및 실행](#빌드-및-실행)
- [요구사항](#요구사항)
- [문제해결](#문제해결)
---
## 유의사항
- 소스를 압축 파일로 제출해주시고, 압축 파일 안의 README.md 파일에 아래 스펙의 구현 여부를 적어주세요.
- Git, SVN 을 사용한다 가정하고 꼭 필요한 파일만 제출하십시오.
- 제출한 소스는 압축을 푼 다음에 mvn clean package 했을 때, JUnit 을 수행하고, JAR 파일을 생성해야 합니다. 그리고 java –jar was.jar 해서 실행할 수 있어야 합니다.
- 아래 스펙을 모두 구현하지 못했더라도 최대한 작성하여 제출하면 됩니다. 전체 구현 완료 여부가 합격, 불합격의 절대 기준은 아닙니다.
- Java 표준 라이브러리 외 다른 네트워크 프레임워크(예, Netty)를 사용하지 말아주세요.
 
---
## 개발 환경
### 기본 환경
- Windows 10
- IntelliJ IDEA Ultimate
### 서버 환경
- JAVA 1.8
- Maven
- JUnit4

---

## 빌드 및 실행
### 사전 작업
- Maven은 설치되어 있다고 가정한다.
- 압축파일을 해체한 위치는 "C:\test\nhn-code-test" 로 가정한다.

### 빌드 방법
```shell
cd C:\test\nhn-code-test
mvn clean package
```

### 실행 방법
```shell
cd target
java –jar was.jar
```

### host 파일 변경 방법
```text
경로 : C:\Windows\System32\drivers\etc
파일명 : hosts
127.0.0.1 bhost.com
```
### 접속 및 테스트
- test 도메인은 임의로 ahost.com / bhost.com 으로 지정.
- ahost.co.kr / ahost.net 등 최상위 도메인은 상관이 없으나 www.ahost.com 등의 서브도메인은 지원않음.
```text
index page : localhost:8080 , bhost.com:8080
hello page : localhost:8080/hello , bhost.com:8080/hello
service.hello page : localhost:8080/service.hello , bhost.com:8080/service.hello
now time page : localhost:8080/Nowtime , bhost.com:8080/Nowtime
```

## 요구사항 및 문제해결
### 문제해결 공통 사항
- 과제 진행 시 전달받은 HttpServer.java , RequestProcessor.java 두개의 파일을 수정하여 진행하였습니다.
- 아래 작성한 코드들은 실제 작성한 코드를 가져왔으며, 내용이 길어지는 부분에 대해서는 getter / setter 을 지우거나, ...중략...으로 표시하였습니다.

### 1. HTTP/1.1 의 Host 헤더를 해석하세요.
   - 예를 들어, a.com 과 b.com 의 IP 가 같을지라도 설정에 따라 서버에서 다른 데이터를 제공할 수 있어야 합니다.
   - 아파치 웹 서버의 VirtualHost 기능을 참고하세요.
#### 문제 해결
- 사용자가 접속한 도메인 정보를 확인하여 홈디렉토리와 설정을 적용하였습니다.
```java
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
```

### 2. 다음 사항을 설정 파일로 관리하세요.
   - 파일 포맷은 JSON 으로 자유롭게 구성하세요.
   - 몇 번 포트에서 동작하는지
   - HTTP/1.1 의 Host 별로
     - HTTP_ROOT 디렉터리를 다르게
     - 403, 404, 500 오류일 때 출력할 HTML 파일 이름
#### 문제 해결
- json 파일로 설정파일을 생성하였습니다.
- 파일명 : config.json
- HTTP_ROOT 의 홈디렉토리내에 해당 오류 발생시 리턴할 페이지를 지정하였습니다.
```json
{
  "port" : 8080,
  "host_info" : [
    {
      "host_name" : "ahost",
      "home_directory" : "/ahost",
      "error_page" : {
        "error403" : "403.html",
        "error404" : "404.html",
        "error500" : "500.html"
      }
    },
    {
      "host_name" : "bhost",
      "home_directory" : "/bhost",
      "error_page" : {
        "error403" : "403.html",
        "error404" : "404.html",
        "error500" : "500.html"
      }
    }
  ]
}
```

### 3. 403, 404, 500 오류를 처리합니다.
   - 해당 오류 발생 시 적절한 HTML 을 반환합니다.
   - 설정 파일에 적은 파일 이름을 이용합니다.
#### 문제 해결
- 오류 발생시 errorHandler을 호출하여 그에 맞는 에러페이지가 출력되도록 하였습니다.
- 접속한 host에 따라 출력되는 에러페이지가 다르도록 하였습니다.
```java
    /**
      * 오류 처리
      * @param res HttpResponse 객체
      * @param errorCode 오류코드
      */
     public void errorHandler(HttpResponse res , String errorCode){
             String fileName ="";
             String responseCode = "http/1.0 ";
             if(errorCode.equals("403")){
                 fileName="403.html";
                 responseCode += "403 Forbidden";
             }else if(errorCode.equals("404")){
                 fileName="404.html";
                 responseCode += "404 Not Found";
             }else {
                 fileName="500.html";
                 responseCode += "500 Server Error";
             }
             StringBuilder stringBuilder = new StringBuilder();
             String content="";
             InputStream inputStream = RequestProcessor.class.getResourceAsStream(rootDirectory+"/"+fileName);
             if (inputStream != null) {
                  try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))) {
                       String line;
                       while ((line = reader.readLine()) != null) {
                         stringBuilder.append(line);
                       }
                  } catch (IOException e) {
                     LOGGER.error("Index.html file Error" , e);
                  }
             }
             res.send(responseCode , "text/html; charset=utf-8" , stringBuilder.toString());
     }

```
### 4. 다음과 같은 보안 규칙을 둡니다.
   - 다음 규칙에 걸리면 응답 코드 403 을 반환합니다.
     - HTTP_ROOT 디렉터리의 상위 디렉터리에 접근할 때,
       - 예, http://localhost:8000/../../../../etc/passwd
     - 확장자가 .exe 인 파일을 요청받았을 때
#### 문제 해결
- ../ 경로와 .exe을 포함한 데이터 요청시 errorHandler메소드를 사용하여 403 에러페이지를 호출하도록 하였습니다.
- ../ 경로의 경우 서버에 요청이 들어온 직후 url을 가져오면 자동적으로 필터리된 상태여서 실제 확인은 불가능합니다. 
- localhost:8080/../../../../hi 호출시 서버에는  GET /hi HTTP/1.1 로 출력됩니다. 
```java
   //접근 권한이 없는 페이지 또는 .exe 파일 접근시 403 리턴
   if(mappingURL.contains("../") || mappingURL.contains(".exe")){
       HttpResponse res = new HttpResponse(out);
       errorHandler(res , "403");
       return ;
   }
```

### 5. logback 프레임워크 http://logback.qos.ch/를 이용하여 다음의 로깅 작업을 합니다.
   - 로그 파일을 하루 단위로 분리합니다.
   - 로그 내용에 따라 적절한 로그 레벨을 적용합니다.
   - 오류 발생 시, StackTrace 전체를 로그 파일에 남깁니다.
#### 문제 해결
- nhn-code-test.log 파일명으로 로그를 기록하도록 하였습니다.
- 하루가 지나면 nhn-code-test-YYYY-MM-DD.log 형식으로 지난 로그 파일이 생성됩니다.
- 로그 레벨은 info로 지정하였습니다.
- 로그 레벨의 수정이 필요한 경우 "nhn-code-test/src/main/resources/logback.xml" 경로에서 root level을 수정하면 됩니다.
- Exception 이 발생할수 있는 위치에서는 로그에 내용을 기록할수 있도록 개발하였습니다.
```java
   try{
       Class<?> clazz = Class.forName(fullMappingURL);
       return clazz.newInstance();
   } catch (ClassNotFoundException e) {
       LOGGER.error("ClassNotFoundException , not found mapping class " , e);
       return null;
   }
```

### 6. 간단한 WAS 를 구현합니다.
   - 다음과 같은 SimpleServlet 구현체가 동작해야 합니다.
     - 다음 코드에서 SimpleServlet, HttpRequet, HttpResponse 인터페이스나 객체는 여러분이 보다 구체적인 인터페이스나 구현체를 제공해야 합니다. 표준 Java Servlet 과는 무관합니다.
   - URL 을 SimpleServlet 구현체로 매핑합니다. 규칙은 다음과 같습니다.
     - http://localhost:8000/Hell- --> Hello.java 로 매핑
     - http://localhost:8000/service.Hell- --> service 패키지의 Hello.java 로 매핑
   - 과제는 URL 을 바로 클래스 파일로 매핑하지만, 추후 설정 파일을 이용해서 매핑하는 것도 고려해서 개발하십시오.
     - 추후 확장을 고려하면 됩니다. 설정 파일을 이용한 매핑을 구현할 필요는 없습니다.
     - 설정 파일을 이용한 매핑에서 사용할 수 있는 설정의 예, {“/Greeting”: “Hello”, “/super.Greeting”: “service.Hello”}
#### 문제 해결
- SimpleServlet 인터페이스를 정의하고 서비스를 제공할 클래스에서 해당 인터페이스를 구현하였습니다.
- service 메소드 안에서 실제 작업할 내용을 작성하면됩니다. 현재는 해당 클래스의 경로와 시간 명칭을 리턴하고 있습니다.
```java
/**
 * SimpleServlet 인터페이스를 구현한 Hello 클래스
 * @author 김성철
 */
public class Hello implements SimpleServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    /**
     * 전달받은 객체를 사용하여 화면에 출력
     * @param req HttpRequest 객체
     * @param res HttpRequest 객체
     */
    public void service(HttpRequest req, HttpResponse res){
        String body = new StringBuilder("<HTML>\r\n")
                .append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
                .append("</HEAD>\r\n")
                .append("<BODY>")
                .append("<H1>")
                .append(this.getClass().getName())
                .append("</H1>\r\n")
                .append("<H2>time : ")
                .append(DateUtil.getTime("yyyy-MM-dd HH:mm:ss"))
                .append("</H2>\r\n")
                .append("<h3>")
                .append(req.getAllParameter())
                .append("</h3>")
                .append("</BODY></HTML>\r\n")
                .toString();
        res.send("HTTP/1.0 200 OK","text/html; charset=utf-8",body);
    }

}
```
- HttpRequest와 HttpResponse 를 구현하였습니다.
- HttpRequest 클래스에서는 쿼리스트링을 받을 수 있도록 하였습니다.
```java
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
     public void setParameter(String name, String value) {
          parameters.put(name, value);
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

```

- HttpResponse 클래스에서는 데이터를 리턴할수 있도록 send 메소드를 정의 하였습니다.
```java
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
```
- URL을 매핑하기위해 makeClassName() , classMapping() , handleRequest() 를 정의하였습니다.
- makeClassName() 메소드는 전달받은 URL 주소에 매핑할 클래스명을 파싱해줍니다.
- classMapping() 메소드는 makeClassName() 메소드에서 생성한 문자열을 사용하여 직접 클래스를 매핑하도록 했습니다.
- handleRequest() 를 사용하여 classMapping() 에서 생성한 클래스를 매핑하도록 하였습니다.
- null인경우 404 페이지를 리턴하도록 하였습니다.
- 추후 설정파일을 사용하여 매핑할 경우 classMapping() 메소드를 수정하여 매핑정보를 읽어와서 매핑되도록 하면 됩니다.
```java
    /**
     * URL 주소로 매핑할 클래스 이름을 생성
     * @param fullClassName 매핑할 클래스 정보
     * @return result 매핑할 클래스 이름
     */
    public String makeClassName(String fullClassName){
        if(fullClassName.indexOf("favicon")>0){
            return null;
        }else if(fullClassName.trim().length()==0){
            return null;
        }
        String [] splitClassName = fullClassName.split("\\.");
        String result="";
        if(splitClassName.length>1){
            for(int i=0;i<splitClassName.length-1;i++){
                result += splitClassName[i]+".";
            }
        }
        String className = splitClassName[splitClassName.length-1];
        if(className.length()>0){
            className = className.toLowerCase();
            className = className.substring(0, 1).toUpperCase() + className.substring(1);
        }
        result +=className;
        return result;
    }


    /**
     * 매핑할 서블릿 클래스 생성
     * @param fullMappingURL 매핑할 클래스 이름(패키지.클래스명)
     * @return object 생성한 서블릿 클래스
     */
    public Object classMapping(String fullMappingURL) {

        try{
            Class<?> clazz = Class.forName(fullMappingURL);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException , not found mapping class " , e);
            return null;
        }catch (Exception e){
            LOGGER.error("classMapping method error " , e);
            return null;
        }

    }

    /**
     * 클래스 메핑
     * @param req HttpRequest 객체
     * @param res HttpResponse 객체
     * @param servletMap 매핑된 서블릿 클래스 정보
     */
    public void handleRequest(HttpRequest req, HttpResponse res , HashMap<String, Object> servletMap) {
        String url = req.getRequestURL();
        SimpleServlet servlet = (SimpleServlet)servletMap.get(url);
        if (servlet != null) {
            servlet.service(req, res);
        } else {
            errorHandler(res , "404");
        }
    }
```
### 7. 현재 시각을 출력하는 SimpleServlet 구현체를 작성하세요.
   - 앞서 구현한 WAS 를 이용합니다.
   - WAS 와 SimpleServlet 인터페이스를 포함한 SimpleServlet 구현 객체가 하나의 JAR 에 있어도 괜찮습니다.
     - 분리하면 더 좋습니다.
#### 문제해결
- 모든 페이지에서 현재 시간을 출력하도록 하였습니다.
- 옆에 기재한 페이지에 접속하여도 됩니다. localhost:8080/Nowtime , bhost.com:8080/Nowtime

### 8. 앞에서 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4 를 이용해서 작성하세요.
#### 문제해결
- Junit4를 사용하여 테스트 케이스를 작성하였습니다.
- 경로 : nhn-code-test/src/test/java/com/nhn