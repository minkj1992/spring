# Annotation을 이용한 Bean 등록
> https://smallgiant.tistory.com/11
## @Controller @repository @service
- @Component
    - 컴포넌트를 나타내는 일반적인 스테레오 타입으로 `<bean>` tag와 동일한 역할을 한다.
    - `@Controller`
        - Presentation Layer
        - Web 어플리케이션에서 Request/Respose 처리하는 클래스에 사용
    - `@repository`
        - Persistence Layer
        - 영속성을 가지는 속성
        - 기존 EJB에서 사용하던 DAO(Presentation Layer의 FACADE)개념을 JPA Entity Manager를 통해서 처리해준다.
        - DDD에서의 REPOSITORY개념은 @Entity에 존재한다.
            - AGGREGATE를 처리하는 Entity를 Root로 설정해서 `CASCADE`적용시킴
            - `@XToXMapping(cascade = CASCADE.TYPE.)`
        - Boot에서는 HikariCp를 통해 DB Connectino Pool 관리를 해준다.
        - 
    - `@service`
        - 다른 기능의 Service/다수의 DAO를 연결
        - 비지니스 로직을 Domain Entity에게 위임
        - Transaction / Cache등을 적용하는 Infra 단위
        

## Bean 의존관계 주입
> 의존하는 객체를 자동으로 주입해주는 anootation
- `@Autowired`
    - 정밀한 의존관계 주입이 필요한 경우
    - Property, Setter, Constructor, Method에 적용 가능
    - 의존하는 객체를 주입할 때 `Type`을 주로 이용
- `@Resource`
    - 어플리케이션에 필요한 자원을 자동으로 연결
    - Property, Setter에 적용가능
    - 의존하는 객체를 주입할 때 `Name`을 주로 이용
- `@Value`
    - 단순한 값을 주입
    - ex) @Value("Spring")은 `<property .. value="Spring" />`와 동일한 역할
- `@Qualifier`
    - `@Autowired`와 같이 사용
    - `@Autowired`는 타입으로 찾아서 주입하므로, 동일 타입의 Bean객체가 여럿 존재할 때 특정 Bean을 찾기 위해 사용