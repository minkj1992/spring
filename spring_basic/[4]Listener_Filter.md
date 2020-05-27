# SpringMVC - Spring MVC 동작원리 - 1
> Servlet의 Listener와 Filter
> https://galid1.tistory.com/521?category=783055

- web.xml에 둘 모두 선언해준다 (listener, filter)
- 아직 spring IoC container를 도입하기 이전 톰캣의 서블릿 컨테이너영역이다.

## Listener
`ServletContextListener`은 Context의 라이프사이클을 감시하는 기능을 제공합니다. 즉, 웹어플리케이션의 시작과 종료시 다음의 메소드가 자동으로 호출 됩니다. `contextInitialized()`, `contextDestoryed()`등

## Filter
> DispatcherServlet 앞단에서 동작

사용자에게 온 요청을 Servlet에게 전달하기 전에 특별한 처리를 할 수 있도록 해줍니다. 이후에 나올 Interceptor와 비교했을 때, Filter에서는 1)인코딩, 2) pc와 모바일웹의 분기처리 3)xss처리 등 가능

- `filterChain.doFilter()`를 해주어야 DispatcherServlet까지 요청이 전달된다.




