package org.abel.webapp.configure;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author JohnsonLam
 * @version 1.0
 */

//import org.abel.webapp.view.*;

public class ActionForward {

  /**
   * 构造函数
   * @param name 对象名字
   * @param path jsp路径
   * @param beanType bean类型
   * @param lifeCycle 传递bean信息时使用的数据区
   */
  public ActionForward(String name, String path) {
  	this.name = name;
    this.path = path;
    
  }

  /**
   * 获取jsp路径
   * @return jsp路径
   */
  public String getPath(){
    return path;
  }


	public void setName(String name){
		this.name = name;	
	}
	
	public String getName(){
		return name;		
	}

	public String toString(){
		return "ActionForward[" + name + "," + path + "]";	
	}	

	public void setTyoe(String type){
		this.type=type;
	}
	
	public String getType(){
		return type;		
	}

	
	private String name;

  /**
   * jsp路径
   */
  private String path;
  private String type ="page";

  public static final ActionForward ActionReport = new ActionForward("_ActionReport_","common/ActionReport.jsp");
}