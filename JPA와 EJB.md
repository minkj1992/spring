# JPA와 EJB의 관계
EJB의 Entity Bean과 달리 JPA는 `POJO` 기반의 ORM이다.

## EJB
- Session Bean
    - 비지니스 로직 담당
    - DB연동이 필요없음
- ENtity Bean
    - DB의 데이터를 관리하는 객체
    - CRUD 및 SELECT
    - DB관련 쿼리는 자동으로 만들어진다.
    - DB가 수정되면 코드 수정없이 재배포됨

- 메시지 구동 Bean(Message-driven Bean)
    - JMS로 Bean을 날려줌

## JPA
- 3가지 영역으로 구성됨
    - javax.persistance 패키지로 정의된 API
    - 자바 persistence Query Language
    - ORM (Object-Relational Mapping)메타 데이터
- 기능
    - 확장된 ORM 가능 (2.0기준)
        - ORM과 다대일 관계로 연결된 내장 객체들의 컬랙션 지원
        - 리스트 정렬
        - 접근 유형 조합
    - POJO기반의 단순한 Persistence Model
        - EJB는 xml config 필요
    - 캐시지원
    - JPQL

- 쉽게 말해 기존의 JDBC등을 이용해 직접 구현했던 DB관련 작업을 대신 처리해 주는 추상화된 계층의 구현 스펙이다.
    - 내부적으로 각종 DB들과 연결할 수 있는 API가 존재하며, JPQL을 통해서 SQL처리가 가능하다.
- JPA 그 자체는 스펙에 불과하기 떄문에 이를 실제로 구현한 제품이나 프레임워크들의 존재가 필수적이다. Servlet라는 기술 스펙을 Tomcat에서 구현한 것 처럼, JPA를 구현한 구현체 중 스프링 부트 프로젝트에는 Hibernate라는 것을 이용하게 된다.


    


