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
   * ���캯��
   * @param name ��������
   * @param path jsp·��
   * @param beanType bean����
   * @param lifeCycle ����bean��Ϣʱʹ�õ�������
   */
  public ActionForward(String name, String path) {
  	this.name = name;
    this.path = path;
    
  }

  /**
   * ��ȡjsp·��
   * @return jsp·��
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
   * jsp·��
   */
  private String path;
  private String type ="page";

  public static final ActionForward ActionReport = new ActionForward("_ActionReport_","common/ActionReport.jsp");
}