# 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발
> https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/lecture/24769

## 1편 - 웹 애플리케이션 개발

- 프로젝트 환경설정
    - [x] 프로젝트 생성
    - [x] 라이브러리 살펴보기
    - [x] View 환경 설정
    - [x] H2 데이터베이스 설치
    - [x] JPA와 DB 설정, 동작확인 (19.12.29)
- 도메인 분석 설계
    - [x] 요구사항 분석
    - [x] 도메인 모델과 테이블 설계
    - [x] 엔티티 클래스 개발
    - [x] 엔티티 클래스 개발2
    - [x] 엔티티 설계시 주의점
- 애플리케이션 구현 준비
    - [x] 구현 요구사항
    - [x] 애플리케이션 아키텍처
- 회원 도메인 개발
    - [x] 회원 리포지토리 개발
    - [x] 회원 서비스 개발
    - [x] 회원 기능 테스트 (19.12.31)
- 상품 도메인 개발
    - [x] 상품 엔티티 개발(비즈니스 로직 추가)
    - [x] 상품 리포지토리 개발
    - [x] 상품 서비스 개발
- 주문 도메인 개발
    - [ ] 주문, 주문상품 엔티티 개발
    - [ ] 주문 리포지토리 개발
    - [ ] 주문 서비스 개발
    - [ ] 주문 기능 테스트
    - [ ] 주문 검색 기능 개발
- 웹 계층 개발
    - [ ] 홈 화면과 레이아웃
    - [ ] 회원 등록
    - [ ] 회원 목록 조회
    - [ ] 상품 등록
    - [ ] 상품 목록
    - [ ] 상품 수정
    - [ ] 변경 감지와 병합(merge)
    - [ ] 상품 주문
    - [ ] 주문 목록 검색, 취소


## error
- `application.yml` comment가 먹히지 않느다.
```yaml

#1. resources 생성하기
#1-1. jdbc in-memory 설정하기 (jdbc:h2:mem:test)
#2. spring_boot 자체에서 yaml 파일에 address 설정이 없다면 자동으로 setting을 해준다.
spring:
  datasource:
    url: jdbc:h2:mem:test
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
#        show_sql: true
        format_sql: true
```
이런식으로 주석을 넣으니까, 계속 에러가 뜬다.

- Intellij 한글 깨짐
```
- x-windows-949로 인코딩 변형시켜준뒤
- 혹시 모르니 git reset --hard 70a475828b838cc239da9505c0e51de1db01701c 로 rollback 시켜주고
- 8080 port process 닫아주니 fuser -n tcp -k 8080

스프링 프로젝트가 돌아가긴 한다, 우선 intellij는 utf-8설정으로 가져야 해야 jvm이 진행된다.
```
- **x-windows-949(cp949)로 인코딩을 바꾸니까 validation의 인코딩이 깨지는 문제가 해결되었다.**


- `Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.NullPointerException] with root cause`
  - 증상
    - ItemForm에다가 insert  - 원인 
    - `java/jpabook/jpashop/controller/ItemController.java`에서 service에 대하여 final을 붙여주지 않아, `@RequiredArgsConstructor`가 작동하지 않았다.
      -
        ```
        /** [INJECTION 4: Lombok 적용]
        @RequiredArgsConstructor: final 설정된 field들만 @Autowired를 생성시점에 적용해준다.
        */
        ```
  - 해결책
    - `private final ItemService itemService;`처럼 final을 붙여준다.를 넣으니까 Null Exception이 생긴다.
  - 원인 
    - `java/jpabook/jpashop/controller/ItemController.java`에서 service에 대하여 final을 붙여주지 않아, `@RequiredArgsConstructor`가 작동하지 않았다.
      -
        ```
        /** [INJECTION 4: Lombok 적용]
        @RequiredArgsConstructor: final 설정된 field들만 @Autowired를 생성시점에 적용해준다.
        */
        ```
  - 해결책
    - `private final ItemService itemService;`처럼 final을 붙여준다.