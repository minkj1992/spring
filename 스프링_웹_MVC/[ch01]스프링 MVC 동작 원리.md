# 스프링 MVC 동작 원리
## 스프링 MVC 소개
- Model(평범한 java 객체, POJO)
    - 도메인 객체(내가 개발하고자 하는 영역을 분석하고, 그 분석의 결과로 도출된 객체, Entity와 VO) 또는 DTO로 화면에 전달할 또는 화면에서 전달 받은 데이터를 담고 있는 객체.
        - 예를 들어 쇼핑몰에서 상품구매에는 사용될 객체
        - Entity(식별자를 가진 객체(상태, 행위), Muttable)와 Value Object(VO, 식별자가 없이 값만 들고 있는 객체)로 나눠짐
            - VO는 식별자가 없어 상태가 변경되면 그냥 다른 객체가 된다. (VO는 Immutable하다.)
        - c.f) DTO vs VO
            - VO는 값으로 취급하는 객체이다. 만약 500원 클래스라면 500원 그자체이다. 이 녀석이 100원이 된다면 아예 다른 객체가 된다. 값으로 취급하는 객체는 상태가 필요없기에 상태변경 행위를 구현하지 않는다. (대표적 String, primitive(Integer, Long..))
            - DTO(Data Transfer Object)는 값을 전달하는 객체이다. getter,setter만 구현되어 있다. 자바에서 뭔가를 하기 위해선 일단 객체를 만들어야 하니 객체화해서 사용할뿐 실질적으로 객체지향과 거리가 있는 객체(DB를 java 객체화 시킨 JPA)
- View
    - 데이터를 보여주는 역할
    - 다양한 형태 가능
- Controller
    - 사용자 입력을 받아 모델 객체의 데이터를 변경
    - 모델 객체를 뷰에 전달
    - 순서
        1. 입력값 검증
        2. 입력 받은 데이터로 모델 객체 상태 변경
        3. 변경된 모델 객체를 뷰에 전달
    
- MVC 패턴의 장점
    - 논리적으로는 묶여있고, 물리적으로는 떨어뜨린다.
        - 높은 응집도 낮은 결합도
    - 이로인하여 개발이 용이하고, 동시 다발적으로 (독립적으로) 개발을 진행 가능
    - 한 모델에 여러 형태의 뷰를 가질 수 있다.

- MVC 패턴의 단점
    - 코드 네비게이션 복잡
    - 높은 학습 곡선( 진입장벽 높다 )
## 서블릿 소개
- 서블릿(고유 대명사)
    - 자바 엔터프라이즈 에디션(Java EE: servlet, JSP, EJB) 구성요소로 웹 애플리케이션 개발용 스펙과 API를 제공
    - CGI(Common GateWay Interface)의 진화형
        - CGI: 일반언어가 웹 서비스를 하기 위한 방식을 미리정의
        - 브라우저는 HTML과 일반 텍스트만 해석가능하기에, CGI변종인 Servlet을 사용
        - CGI는 요청 당 프로세스를 만들어 사용
    - 대표적으로 HttpServlet
        - 요청 당 쓰레드(만들거나, Pool에서 가져다가) 사용
        - **프로세스를 통해서 자원 공유 가능**
    - servlet 엔진(컨테이너)와 애플리케이션(흔히 서블릿(고유 대명사와 다른 기능적 이름))이 구성요소로 존재한다.
        - 컨테이너가 쓰레드와 servlet을 엮어서 여러 servlet.service()를 관리한다.


- 장점(vs CGI)
    - 빠르다
    - 플랫폼 독립적 (JVM 기반)
    - 보안
- 서블릿 엔진(서블릿 컨테이너, ex)톰캣,제티,undertow)
    - ```
        - 사실 톰캣, 제티 같은 서버를 서블릿 컨테이너라고 부르는 것은 제한적이다. 왜냐면 톰켓은 서블릿 뿐 아니라 JSP 같은 것도 실행하니까.
        - 또한 톰켓은 WAS(web server(정적) + web container(servlet Container, 동적))이고, 서블릿 컨테이너는 tomcat에서 web Container를 담당한다.
        - apache는 오픈소스 프로젝트로, 이 중 apache http웹서버 프로젝트가 대표적이다. 아파치 http 서버는 웹서버(정적)로써 HTTP Method를 이용해 respond한다.
        ```
    - session 관리
    - 네트워크 서비스
    - MIME 기반 메시지 encoding/decoding
        - MIME
            - Multipurpose Internet Mail Extension
            - 전자 우편을 위한 인터넷 표준 포맷
            - text,binary들은 MIME으로 포맷되어 SMTP로 전송
            - 등장배경: ASCII로만 텍스트 파일을 주고받았던 이전 프로토콜과 다르게 바이너리 파일(음악,영상,워드 등)을 전달이 필요하게 되며, 이런 mixed data를 ASCII로 표현(encoding)시켜 기존 시스템을 통해 문제 없이 전달하고 req받은 쪽에서 decoding(Binary로 변환)하여 다시 데이터를 복구하도록 함.
            - HTTP/1.0 부터 다른 타입의 콘텐츠를 전송가능하게 되었는데, 이는 MIME을 도입하여 가능하게 하였다.
    - 서블릿 생명주기 관리
        1. 서블릿 컨테이너가 서블릿 인스턴스 `init()`(이후에는 생략)
        2. 서블릿 초기화 이후 client req 처리 가능하며 각 요청은 별도의 쓰레드로 처리하고, 이때 서블릿 인스턴스의 `service()` 메소드 호출
            - 컨테이너가 쓰레드를 생성하고 service()를 호출시켜주는 듯
            - 서블릿 인스턴스의 service 안에서 HTTP 요청을 받고 클라이언트로 보낼 HTTP 응답을 생성
            - service()는 HTTP Method에 따라 `doGet()`, `doPost()`등으로 처리를 `위임`한다.
                - `deligate(위임)`: 개발 당시 특정 기능 구현이 완료되지 않았어도, 만들어졌다고 가정하고 interface(추상적인 접근점)을 호출해 기능을 수행하도록 한다. (`Strategy Pattern`: strategy를 interface에 deligate하고 interface는 여러 strategy 구현체들 중 올바른 구현체를 고른다.)
            - 따라서 보통 `doGet()`, `doPost()`을 구현한다.
        3. 서블릿 컨테이너 판단에 따라 해당 서블릿을 메모리에서 내려야 할 시점에 `destroy()` 호출

## 서블릿 애플리케이션 개발 
- servlet java 코드 생성
    - init()
    - doGet()
        - html을 string으로 HttpServletResponse.getWriter()에 넣어준다.
    - destroy()
- web.xml
    - 서블릿 등록(url 설정 명시)

- run config를 tomcat server로 지정
    - war exploded(war를 풀어헤쳐서 실행하는 방법)

## 서블릿 리스너와 필터
- servlet context
    - servlet attribute
- servlet application
- servlet listener
    - servlet context listener
    - session listener
- servlet filter
    - servlet instance init() 이후, client request가 servlet instance에 올 때, 먼저 filter들을 chainning해서 지나간 뒤, instance로 도달한다.
    
## 스프링 IoC 컨테이너 연
## 스프링 MVC 연동
## DispatcherServlet 1부  
## DispatcherServlet 2부  
## DispatcherServlet 3부  
## 스프링 MVC 구성 요소 
## 스프링 MVC 동작 원리 마무리 