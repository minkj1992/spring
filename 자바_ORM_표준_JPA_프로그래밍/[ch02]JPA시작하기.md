# Hello JPA

## 프로젝트 생성
> `./study/ex1-hello-jpa`

- H2 DB설정
- Maven 설정
    - persistence.xml
    - porm.xml

## 애플리케이션 개발

- `h2` 설정
    - JDBC_URL: `jdbc:h2:~/test`
        ```
        Database "/home/minkj1992/test" not found, either pre-create it or allow remote database creation (not recommended in secure environments) [90149-200] 90149/90149 (도움말)
        ```
        - 계속 에러가 떠서, tcp 통신이 아닌, file을 통한 접근을 하니 바로 접근이 가능하였다.
        - 다만 중간에 root 근처에 존재하지 않고 code/spring/어딘가에 있던 `h2`폴더를 root쪽으로 옮겨주었는데, 그럴 필요 없는것 같다.
        - `chmod +x h2.sh`또는 `chmod 755 h2.sh`해주어야 됨

        - tcp 통신은 방화벽
        - [방화벽](https://demoversion.tistory.com/69)
        - [파일참조](https://secr.tistory.com/492)
        - 정말인지 인강 설명 초보자가 듣기에는 짜증날 것 같다. 단축키도 그렇고 이런 JDBC_URL관련된 설명 방화벽 부터 설정해줘야할게 얼마나 많은데,거기다가 h2.sh 권한 설정 관련된 말도 싹다 빼먹으셨다. UBUNTU는 오늘도 구글링

- CRUD
    1. `ManagerFactory`, `Manager`
        - Manager는 DB커넥션이라 생각하면 됨
        - 
    2. try..catch..finally를 활용한 기본 트랜잭션 형식
    3. **UPDATE시에는 `persist();`할 필요가 없다.**
        - Java 객체에서(`@Entity`)값 만 바꿔줘도, JPA가 `commit()`타임때 수정 여부를 체크하고 알아서 수정 쿼리를 쏴준다.

- `JPQL`
    - 현업에서 사용할 복잡할 쿼리
    - Entity 객체(Object)를 대상으로 날릴 수 있는 query
        - 다른 RDBMS 벤더에 종속되지 않는다.