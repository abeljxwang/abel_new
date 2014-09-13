/*
 * 创建日期 2004-3-4
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.common;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
