/*
 * �������� 2004-3-4
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package org.abel.webapp.policy.common;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ConditionItem {

	//	<Condition Type="AND">
	//	<Expression Value="a == ${a}" />
	//	</Condition>
	String algorithm = "AND";
	String cddesc = "";

	public ConditionItem(String alg, String cds) {
		algorithm = alg;
		cddesc = cds;
	}

	public boolean parsing() {
		return false;
	}
	public static void main(String[] args) {
	}
}
