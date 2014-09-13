/*
 * 创建日期 2003-11-3
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.base;

import java.util.*;
/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class PolicyBox {

	/**
	 * 
	 */
	Properties pilicies = new Properties();

	public void set(String key, Policy p) {
		pilicies.put(key, p);

	}

	public Policy get(String key) {
		return (Policy) pilicies.get(key);

	}

	public PolicyBox() {
		super();

	}

	public static void main(String[] args) {
	}
}
