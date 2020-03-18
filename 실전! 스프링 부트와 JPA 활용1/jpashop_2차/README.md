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

1. API 개발
- Entity는 자주 바뀌는데, Entity때문에 REST 스펙이 바뀌는 문제가 존재