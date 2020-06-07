# HandlerMapping, HandlerAdapter, HandlerInterceptor
> [참고 자료](https://joont92.github.io/spring/HandlerMapping-HandlerAdapter-HandlerInterceptor/)

목표: Interceptor, Mapper, Adapter가 Spring Flow에서 정확히 어디 위치에 존재하며, 구체적으로 어떤식으로 동작하는지를 알아본다.

## `HandlerMapping`
> 요청을 처리할 Controller를 가리키는 Handler를 반환

`DispatcherServlet`은 가장 먼저 `HandlerMapping`의 전략으로 취한 전략 bean들에게 HTTP 요청정보를 담고있는 `HttpServletRequest`을 전달하여 Object타입의 `Handler`를 return 한다. 

이때 Handler를 찾는 방법은 annotaion기준으로 찾는 `DefaultAnnotationHandlerMapping` 전략이 존재하는데, 이 전략은  `@RequestMapping`, `@GetMapping`등의 Annotation으로 등록된 Controller를 찾고, 최종적으로 실행할 메서드는 `AnnotationHandlerAdapter`가 결정하도록 위임한다.

## `HandlerAdapter`
HandlerMapping을 통해 찾은 컨트롤러를 직접 실행하는 기능을 수행한다. 핸들러 어댑터는 HandlerAdapter `인터페이스`를 구현해서 생성한다.

```java
package org.springframework.web.servlet;

public interface HandlerAdapter {
    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    long getLastModified(HttpServletRequest request, Object handler);
}
```

[handler-adapter](https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/handler-adapter.html) 글에 따르면 

 `@EnableWebMvc`를 사용하면서 부터
 
1. `requestMappingHandlerAdapter`로는 `RequestMappingHandlerAdapter`를 사용

2. `httpRequestHandlerAdapter` 로는 `HttpRequestHandlerAdapter`를 사용

3. `simpleControllerHandlerAdapter`로는 `SimpleControllerHandlerAdapter`를 사용

한다고 한다. 아래 소스코드를 통해서 확인해보면 같은 결과가 나오는 것을 확인할 수 있다. 참고로 **`@RequestMapping`을 사용할 때 DispatcherServlet이 `RequestMappingHandlerAdapter`를 사용한다.**

```java
@Controller
public class TestController {
  
  @Autowired
  ApplicationContext context;
  
  @RequestMapping(value = "/test")
  @ResponseBody
  public String handleRequest () {
      Map<String, HandlerAdapter> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(
                context, HandlerAdapter.class, true, false);
      
      matchingBeans.forEach((k, v) -> System.out.printf("%s=%s%n",
                                                        k,
                                                        v.getClass().getSimpleName()));
      return "response from /test";
  }
}
```

    Accessing http://localhost:8080/test prints following output on the server console:

    requestMappingHandlerAdapter=RequestMappingHandlerAdapter
    httpRequestHandlerAdapter=HttpRequestHandlerAdapter
    simpleControllerHandlerAdapter=SimpleControllerHandlerAdapter


3가지 adapter에 대해서 추가설명을 하자면, 

`RequestMappingHandlerAdapter`
> 이 HandlerAdapter는 @RequestMapping으로 주석 처리 된 핸들러 클래스 / 메소드를 대상으로 한다. 주어진 핸들러 유형 (HandlerMapping에서 리턴 됨)이 HandlerMethod 인 경우 supports () 메소드가 true를 리턴되어 class/method를 구분한다. HandlerMethod는 핸들러 메소드 / 매개 변수 / 반환 유형에 대한 정보를 랩핑하고 @Controller Bean을 둘러싸고(Wrap)하고 있다.

`HttpRequestHandlerAdapter`
> HttpRequestHandler 유형의 핸들러 오브젝트를 지원

`SimpleControllerHandlerAdapter`
> Controller 유형의 핸들러 오브젝트를 지원