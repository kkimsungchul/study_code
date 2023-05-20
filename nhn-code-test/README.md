# NHN 채용 사전과제 전형_Java
## 목차
- [유의사항](#유의사항)
- [개발 환경](#개발-환경)
- [빌드 및 실행](#빌드-및-실행)
- [요구사항](#요구사항)

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
- 압축파일을 해체한 위치는 "C:\test" 로 가정한다.

### 빌드 방법
```shell
cd C:\IntelliJProject\study_code\nhn-code-test
mvn clean package
```

### 실행 방법
```shell
cd C:\IntelliJProject\study_code\nhn-code-test\target
java –jar was.jar

```

### host 파일 변경 방법
```text
경로 : C:\Windows\System32\drivers\etc
파일명 : hosts
```

## 요구사항