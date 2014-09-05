/*
 * 创建日期 2004-3-4
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.common;

import java.util.Vector;
//import java.util.Properties;

import org.abel.webapp.policy.base.Operator;


/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class Condition {

	//	<Condition Type="AND">
	//	<Expression Value="a ==0" />
	//	</Condition>
	//String algorithm = "AND";
	String algorithm = "AND";
	String cddesc = "";
	Vector items = new Vector();

	//String value="a EQ 0";

	public Condition() {
	}
	public Condition(String alg, String cds) {
		algorithm = alg;
		cddesc = cds;
	}

	public void addItem(String item) {
		items.add(item);
	}

	public Vector getAllItem() {
		return items;
	}

	public boolean parsing(BaseDO bdo, WFParameters p) {
		String tmp = null;
		boolean result = true;
		for (int i = 0; i < items.size(); i++) {
			tmp = items.elementAt(i).toString();
			result =
				Operator.getBooleanResult(
					result,
					parsingItem(tmp, bdo, p),
					algorithm);
			// parsingItem(tmp);
		}
		return result;
	}

	public boolean parsingItem(
		String item,
		BaseDO bdo,
		WFParameters p) {
		CItemParser ps = new CItemParser();
		ps.getparameters(item);
		boolean result = false;
		Vector aaa = ps.complete(bdo, p);
		if (aaa.size() > 0) {

			String tmp1sttag = aaa.elementAt(0).toString();
			String tmp2ndtag = aaa.elementAt(1).toString();
			String tmp3rdtag = aaa.elementAt(2).toString();

			result = Operator.getResult(tmp1sttag, tmp3rdtag, tmp2ndtag);
		}
		return result;
	}

	public boolean parsingItem(String itemstr) {
		//itemstr.indexOf()
		return false;
	}

	public static void main(String[] args) {

		//ConditionSet cst=new ConditionSet();
		Condition cnd = new Condition();
		cnd.addItem("TARGETFLAG EQ '02'");
		//cst.set(cnd);

		//tran.setConditionSet(cst);

		BaseDO bdo = new BaseDO();
		bdo.setParameter("TARGETFLAG", "02");
		WFParameters wfp = new WFParameters();


		System.out.println(cnd.parsing(bdo, wfp));

	}
}
