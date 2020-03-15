# Service Layer와 Domain Object
> https://wckhg89.tistory.com/13

Service Layer안에 Business Logic이 있어야 한다 주장하지만, 이는 절차지향적 개발에 가깝다 볼 수 있다. ( 순차적, 프로그램 전체가 유기적 연결) 즉 Business Logic을 Service에서 구현하고 Domain은 Getter/Setter만 제공하는 방식이다.

객체지향적 개발이란 **객체에게 해야할 일을 위임하는 것**이다. 즉 service에서는 특정 비지니스 로직을 Domain 객체에 위임시켜주어야 한다. 

- Service Layer
    1. 다른 기능의 Service를 호출하거나 다수의 DAO를 연결하는 역할
    2. Transaction과 Cache등을 적용하는 Infra 단위
    3. Service는 가능한 가볍게 구현한다. (thin layer)
    4. **비지니스 로직은 상태 값을 가지고 있는 도메인 모델이 담당해야 한다.**

**OOP에서 객체는 State(상태) + Action(행위)를 담당한다.**
