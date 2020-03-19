# 프로젝트 진행

## Error

- `org.springframework.beans.factory.BeanCreationException` -> 해결
    - **Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]**
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]
```
- `OrderItem`
    - `@Table(name = "order_item")`추가
    - `Order order`필드에 `@ManyToOne` 추가

## 200318
> REST API 
1. 회원등록 
- Entity는 자주 바뀌는데, Entity때문에 REST 스펙이 바뀌는 문제가 존재
- API를 만들때는 항상 Entity를 parameter로 받지말라!
- 또한 Entity를 외부에 노출해서도 안된다.
- Entity와 Presentation계층을 분리하고, REST API에 강제하고 싶은 값은 DTO단에서 Validation강제해주어 모든 통신에서 처리되도록 한다. (부분 커스터마이징 가능)

2. 회원 수정
- HTTP Method
	- PUT은 같은 변경을 여러번 해도 변경되지 않는다.
	- POST는 ID가 변경될까?
- updateDTO
	- Entity에서는 @Getter정도만 사용하지만, DTO에는 좀 막쓴다.
	- STATIC 사용하는 이유는?
	- **Response에만 Constructor가 있어도 되는 이유는? 어쩌면 Parameter로 전달되면서 Request는 자동으로 set되는 것 같다. (@DATA)에 의해서?**
- `updateMemberV2()`
	- update는 변경감지 로직

- `UpdateMemberResponse`
	- **command 와 query는 분리한다.**

3. 회원 조회

- `public List<Member> membersV1() {}`
	- 문제점
		- 엔티티를 직접 노출하면 안된다.
		- 순수하게 회원정보만 원하는데, 우선 Orders가 들어있다.
		- 회원과 관련된 조회 api는 정말 다양하게 (orders가 필요한 API) 있을텐데, 감당 안되고 Entity에 Presentation Logic이 들어오면 감당 안된다.
		- Entity의 name변수가 userName으로 변경되면 API 받는 다른 클라이언트들은 Json `Username: `을 입력 받는다.
		- array가 바로 반환되어서 왔다.(JSON 스펙 확장 깨진다, 만약 count넘겨달라하면 못함)

- `membersV2()`
- class `Result`
	- 한번 class로 감싸서 내보내야 json이 유연해진다. 배열타입으로 바로 내보내면 JSON은 array타입으로 받는다.
	- 만약 count같은 값이 필요하다면 Result에다가 Count 추가하면 된다.
- class `MemberDto`

4. API 개발 고급





- indexing jpa 방식
- Redis와 jpa Persistence Context(cache) 차이 그리고 session 관리는 어떻게 MVC에서 처리해주는가
- 분산 DB에서는 JPA Persistence 관리 어떻게 하는가?
