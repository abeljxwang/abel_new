/*
 * 创建日期 2003-11-29
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package org.abel.webapp.policy.meta;

import java.util.*;
/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class BaseMeta {

	/**
	 * 
	 */
	public BaseMeta() {
	}
	Properties meta = new Properties();

	public void set(String key, Object value) {
		meta.put(key, value);

	}

	public Object get(String key) {
		return meta.get(key);

	}
}
