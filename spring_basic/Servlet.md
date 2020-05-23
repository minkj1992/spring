# 톰캣 환경 설정

##  (`DispatcherServlet`)
> URL에 맞는 Controller를 찾아 쓰레드/프로세스를 실행시켜준다.

URL이 들어온다면 DispatcherServlet 객체의 특정 메서드가 실행된다. 서블릿 생명주기에 맞게 init(), service()(doGet(), doPost())가 실행된다.

DispatcherServlet은 모든 요청을 받아들이는 Servlet이다. DispatcherServlet이 모든 요청을 받아들여 적절한 메서드가 실행될 수 있도록 분기시킨다

