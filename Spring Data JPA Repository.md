# Spring Data JPA Repository
> https://memories95.tistory.com/136?category=832097

JPA를 이용하면 별도의 클래스 파일없이 인터페이스를 구현하는 것만으로도 JPA와 관련된 모든 처리가 끝난다. 일반적으로 DAO 개념을 이용하듯 JPA는 이를 이용한 Repository라는 용어가 있다.

- 전통적으로 EntityManager를 구성하고 트랜잭션을 시작하고 종료하는 코드를 만들어 JPA처리를 하는 방법이 있다.
- 간편하게 `Repository`를 구성함으로 이를 처리할 수 있는 방법이 있다.
    - Repository<T,ID>
        - CrudRepository<T,ID>
            - CRUD 담당
            - PagingAndSortingRepository <T,ID>
                - 페이징 처리, 검색 처리

