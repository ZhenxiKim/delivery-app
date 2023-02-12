## 사용 기술

--- 
java11, spring-boot(2.6.13), H2, JPA, Gradle, Junit5, Spring Security

## 패키지 구조

--- 
```
── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── demo
    │   │           └── deliveryapp
    │   │               ├── DeliveryAppApplication.java
    │   │               ├── config
    │   │               ├── controller
    │   │               ├── domain
    │   │               ├── exception
    │   │               ├── repository
    │   │               ├── security
    │   │               └── service
    │   └── resources
    │       ├── application.yml
    │       └── data.sql
    └── test
        └── java
            └── com
                └── demo
                    └── deliveryapp
                        ├── DeliveryAppApplicationTests.java
                        └── service
```

## 기능 목록

--- 
- 회원 가입 API 
  - 회원가입시 필요한 정보는 ID, 비밀번호, 사용자 이름
  - 비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로 12자리 이상의 문자열로 생성
  - 
  
- 로그인 API
  - ID, 비밀번호를 입력받아 로그인을 처리
  - 이미 가입 된 회원일 경우 로그인이 되었다는 응답으로 AccessToken 을 제공
  - 

- 배달 조회 API
  - 기간으로 배달 조회 (기간은 최대 3일)
  - 기간 내에 사용자가 주문한 배달의 리스트를 조회


- 배달 주문 수정 API
  - 도착지 주소를 요청 받아 수정
  - 변경가능한 배달일 경우만 수정
## swagger 실행 방법

--- 

- http://localhost:8080/api-docs

## ERD

--- 
- https://www.erdcloud.com/d/wkMuMLgee6r3XEyAL
![delivery_app](https://user-images.githubusercontent.com/45592236/218294765-40bc8aa1-d46d-4eda-bdfa-b8bdd9e05e72.png)


