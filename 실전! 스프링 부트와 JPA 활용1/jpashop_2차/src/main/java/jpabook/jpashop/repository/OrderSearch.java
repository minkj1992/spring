package jpabook.jpashop.repository;
// ���� ����

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;  //ȸ�� �̸�
    private OrderStatus orderStatus;    //�ֹ����� [ORDER,CANCEL]
}
