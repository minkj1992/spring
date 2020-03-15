# Entity, EntityManager, Persistence_Context
> https://memories95.tistory.com/134?category=832097

- Domain (DDD에서의 개념)
    - 
    - 도메인 객체
    - 개발하고자 하는 영역에서 객체를 도출하였을때 나오는 객체
    - 쇼핑몰 기능중 상품 구매(도메인)
    - 종류
        - Entity
        - VO
- Entity(DDD, JPA..)
    - 식별자를 지님
    - 데이터베이스 상에서 데이터로 관리하는 대상을 뜻함
    - JPA에서는 POJO로 엔티티들을 관리하기 떄문에, 엔티티 타입의 존재는 클래스가 된다.
    - 하나의 엔티티 타입을 생성한다는 것은 하나의 클래스를 작성한다와 같다.
- Entity Manager(JPA)
    - 여러 엔티티 객체들의 Life Cycle을 관리
    - 엔티티 매니저는 자신이 관리해야 하는 엔티티 객체들을 `Persistence Context`에 두고 Life Cycle을 관리한다.
- 참고로 JPA는 JAVA와 달리 `동적 프록시(Dynamic Proxy)`를 통해 동적으로 인터페이스를 구현하는 클래스를 만들어 내기 때문에, 실제 클래스를 작성하지 않아도 자동으로 클래스가 생성된다.
