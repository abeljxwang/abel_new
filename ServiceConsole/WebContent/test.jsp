<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@ page
	import="java.util.*,java.sql.*"%>


<%

  Connection conn=null;

  Statement stmt =null;

  ResultSet rs=null;

   

  try{

              Context initContext = new InitialContext();

              Context envContext=(Context)initContext.lookup("java:/comp/env");

              DataSource ds = (DataSource)envContext.lookup("jdbc/AbelDB");

              conn = ds.getConnection();

              if(conn!=null){

                       System.out.println("create connection  sucess!");

                       stmt = conn.createStatement();

                       if(stmt!=null){

                                 System.out.println("create stmt sucess!");

                                  rs=stmt.executeQuery("select * from Category");

                                 while(rs.next()){

                                            out.println(rs.getString(1)+"<br>");

                                 }

                       }else{

                                 System.out.println("create stmt fail!");

                       

                       }

               }else{

                       System.out.println("create stmt fail!");

              }

  }catch(Exception e){

             System.out.println(e);

  }

  finally{

             if(rs!=null)

                        rs.close();

             if(stmt!=null)

                        rs.close();

             if(conn!=null)

                        conn.close();

  }

%>
