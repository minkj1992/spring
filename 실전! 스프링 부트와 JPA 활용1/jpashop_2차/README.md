# 프로젝트 진행

## Error

- `org.springframework.beans.factory.BeanCreationException` -> 해결
    - **Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]**
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1771) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:593) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1105) ~[spring-context-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:867) ~[spring-context-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:549) ~[spring-context-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:744) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:391) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1204) ~[spring-boot-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
	at jpabook.jpashop.JpashopApplication.main(JpashopApplication.java:11) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.1.14.BUILD-SNAPSHOT.jar:2.1.14.BUILD-SNAPSHOT]
Caused by: javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.MappingException: Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:403) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:378) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1830) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1767) ~[spring-beans-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	... 21 common frames omitted
Caused by: org.hibernate.MappingException: Could not determine type for: jpabook.jpashop.domain.Order, at table: order_item, for columns: [org.hibernate.mapping.Column(order)]
	at org.hibernate.mapping.SimpleValue.getType(SimpleValue.java:486) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.mapping.SimpleValue.isValid(SimpleValue.java:453) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.mapping.Property.isValid(Property.java:227) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.mapping.PersistentClass.validate(PersistentClass.java:624) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.mapping.RootClass.validate(RootClass.java:267) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.boot.internal.MetadataImpl.validate(MetadataImpl.java:347) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:466) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1250) ~[hibernate-core-5.3.15.Final.jar:5.3.15.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:391) ~[spring-orm-5.1.14.RELEASE.jar:5.1.14.RELEASE]
	... 25 common frames omitted
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
	- 
