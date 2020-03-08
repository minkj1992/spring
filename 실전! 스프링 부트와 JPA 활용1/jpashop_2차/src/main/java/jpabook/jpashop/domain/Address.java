package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

// Address class�� ������ҵ��� �ϳ��� ��� ��ü�� ���� -> �̸� �����Ͽ� �����ϴ� ��ü�� @Embedded
@Embeddable
@Getter
// JPA���� �⺻������ �����ڰ� �����ؾ� ���÷���, ���Ͻ̰� ���� ����� �� �� �־�, �⺻ �����ڸ� ������ش�.
// �ٸ� protected���� ������־�, ����ڰ� �Ժη� ������ ���� ���ϵ��� �Ѵ�.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// ��Ÿ���� ������ ���� set ���ְ�, setter�� ���ؼ� ����Ǹ� �ȵȴ�.
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
