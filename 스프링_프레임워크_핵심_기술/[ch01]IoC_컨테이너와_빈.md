# 1. IoC 컨테이너와 빈

## [1-1] IoC와 DI
> IoC란 어떤 객체가 사용하는 의존 객체를 직접 만들어 사용하는게 아니라, 주입 받아 사용하는 방법

- `IoC`
    - 이전에는 개발자가 프로그램의 흐름을 제어하는 주체
    - 스프링에서는 프로그램의 흐름을 프레임워크가 주도
        - 객체 생성
        - 생명 주기
        - 의존성 관리
    - **즉 제어권이 컨테이너로 넘어가게 된다.**
    - 이를 통해 DI, AOP등이 가능해 진다.
- `DI`
    - 객체간의 의존성을 외부에서 주입하는 개념
        - `의존성`이란?
            - 코드에서 두 모듈 간의 연결
            - 하나의 모듈이 바뀌면 의존한 다른 모듈까지 변경을 해주어야 한다는 위험성
            - 유닛테스트 작성을 어렵게 한다.(유닛테스트 목적이 독립적인 모듈을 생성하기 위해서 존재, `Mock` 객체를 통해 대체 가능)

## [1-2] 스프링 IoC 컨테이너와 Bean
- `IoC 컨테이너`
    - `BeanFactory` 인터페이스
    - 애플리케이션 컴포넌트의 중앙 저장소
    - Bean 설정 src로 부터 Bean 정의를 읽어들이고, Bean을 구성한뒤 제공한다.
- `Bean`
    - 스프링 IoC 컨테이너가 관리하는 객체
    - Spring `Annotation`으로 표기해야함
    - 장점
        - 의존성 관리
        - Scope 관리 가능
            - `싱글톤`: default
            - `프로토 타입`(매번 다른 객체를 생성)
        - 라이프사이클 인터페이스 제공

- `ApplicationContext` 인터페이스

## [1-3] ApplicationContext (Bean을 등록하는 여러가지 방법들)
> [연습코드](./Exercise/springapplicationcontext)


[출처 Blog](https://seongmun-hong.github.io/spring/Spring-Bean-Create)

1. 우선 Spring boot Project 만들고  
2. BookService, BookRepository를 생성   
#### BookRepository.java  
  
```java
public class BookRepository {
}
```  
  
#### BookService.java  
  
```java
public class BookService {

    BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
```  

<br />

### 1. xml - Bean 태그 이용  
  
- Resource 폴더에 application.xml 생성 
  
#### application.xml  
  
-
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans ...>
        <bean id="bookService"
            class="com.springstudy.springapplicationcontext.BookService">
            <property name="bookRepository" ref="bookRepository"/>
            <!--name="bookRepository" 이부분에서 bookRepository는 bookService의 setter에서 가져옴-->
            <!--ref="bookRepository" 이부분에서 bookRepository는 bean id 에서 가져옴-->
        </bean>

        <bean id="bookRepository"
            class="com.springstudy.springapplicationcontext.BookRepository">
        </bean>
    </beans>
```  
- <property name=<SETTER에 사용된 ref> ref=<SETTER를 채울 BEAN ID>/>
- `<property name="bookRepository" ref="bookRepository"/>`

  
<br />

- xml 파일을 로드하여 Application 실행

#### DemoApplication.java  
  
```java
public class DemoApplication {

    public static void main(String[] args) {

       ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
       String[] beanDefinitionNames = context.getBeanDefinitionNames();
       System.out.println(Arrays.toString(beanDefinitionNames)); // bean name 출력
       BookService bookService = (BookService)context.getBean("bookService");
       System.out.println(bookService.bookRepository != null); 
       // bookService의 bookRepository의 의존성 주입 여부 확인

    }

}
```  

<br />

## 2. XML - componentScan 사용  
  
- application.xml 수정  
  
#### application.xml  
  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans ...>
    <context:component-scan base-package="com.springstudy.springapplicationcontext"/>
    <!--Spring 2.5부터 가능했던 기능으로 bask-package에서부터 @Component 혹은 Component를 상속받은 -->
    <!--Repository, Service 등의 어노테이션을 찾아 빈으로 등록한다.-->
</beans>
```  
  
<br />

- Bean Class에 어노테이션 작성  
 
#### BookRepository.java
  
```java
@Repository
public class BookRepository {
}
```  
 
#### BookService.java
  
```java
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
```  
  
<br />

## 3. Config파일을 통한 Bean 등록  
  
- ApplicationConfig.java 파일 생성 및 Bean 생성  
  
#### ApplicationConfig.java  
  
```java
@Configuration // bean을 등록하는 Configuration 이다.
public class ApplicationConfig {

   @Bean
   public BookRepository bookRepository() {
       return new BookRepository();
   }

   @Bean
   public BookService bookService() {
       BookService bookService = new BookService();
//        아래와 같이 직접 의존성을 주입하지 않아도 @Autowired를 통하여 주입할 수 있다.
//        하지만 BookService에 setter메서드가 있어야 가능하다 !
//        bookService.setBookRepository(bookRepository());
       return bookService;
   }

}
```  
  
<br />

- ApplicationConfig를 로드하여 Application 실행  
  
#### DemoApplication.java  
  
```java
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

      // annotation을 통한 bean 등록
       ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
       String[] beanDefinitionNames = context.getBeanDefinitionNames();
       System.out.println(Arrays.toString(beanDefinitionNames)); // bean name 출력
       BookService bookService = (BookService)context.getBean("bookService");
       System.out.println(bookService.bookRepository != null); 
       // bookService의 bookRepository의 의존성 주입 여부 확인

    }

}
```  
  
<br />

## 4. Config의 ComponentScan을 활용한 Bean 등록  
  
- ApplicationConfig.java 파일 생성 및 Bean 생성  
  
#### ApplicationConfig.java
  
```java
@Configuration // bean을 등록하는 Configuration 이다.
//@ComponentScan(basePackages = "com.springstudy.springapplicationcontext")
//위의 방법은 type safety 하지 않으므로 아래의 방법을 추천한다.
@ComponentScan(basePackageClasses = DemoApplication.class)
// DemoApplication 클래스가 위치한 곳부터 scan
public class ApplicationConfig {

}

```  

<br />
  
- BookService에 Autowired를 통한 DI 주입  
  
#### BookService.java
  
```java
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
```  

<br />
  
  
## @SpringBootApplication Annotation 사용  
  
4번의 방법을 통하여 간단하게 Bean을 등록하는 방법은 @SpringBootApplication Annotation을 사용하는 것이다.  
  
해당 어노테이션의 클래스에 들어가 확인해 보면 아래와 같다.  
  
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
...
```  

<br />
  
위와 같이 ComponentScan 어노테이션이 들어가 있는 것을 확인할 수 있다.  
  
따라서 DemoApplication.java 파일을 아래와 같이 수정하면 된다.  
<br />
#### DemoApplication.java  
  
```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

}
```  

## [1-4] @Autowire
> [연습코드](./Exercise/deomspring51)

- [정리된 blog](https://seongmun-hong.github.io/spring/Spring-IoC-Container(2))

@Autowire를 통해 의존성 주입을 해보자.
그전에 이때 생길 수 있는 대표적인 에러는 다음과 같다.

```
Parameter 0 of method setBookRepository in me.minkj1992.deomspring51.BookService required a bean of type 'me.minkj1992.deomspring51.BookRepository' that could not be found.
```
- 해결법: repo가 되는 class에 @Reposotory를 붙여주면 된다.


- @Autowire를 통한 의존성 주입
    - 1. 생성자 @Autowire
    ```java
        @Autowired
        public BookService(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }
    ```

    - 2. Setter @Autowire
    ```java
        @Autowired
        public void setBookRepository(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }
    ```
 

