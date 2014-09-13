package org.abel.service.base.common;

/**
 * ?????????
 * ????:(2001-11-10 22:33:57)
 * @author:
 */
public class ObjectFoundy {
	
/**
 * NewObj ??????
 */
public ObjectFoundy() {
	super();
}
/**
 * ?????????
 * ????:(2001-11-10 22:40:00)
 * @return java.lang.Object
 * @param p java.lang.String
 * @exception java.lang.InstantiationException ?????
 * @exception java.lang.IllegalAccessException ?????
 * @exception java.lang.ClassNotFoundException ?????
 */
public static Object factory(String p) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
	Class c=Class.forName(p);
	Object o=c.newInstance();
	//Inrerface ii=Interfac
	return o;
}
}


