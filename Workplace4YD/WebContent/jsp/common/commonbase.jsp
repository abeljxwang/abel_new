

<%!
	
	public String toString(String aa){
		String rt=null;
		if(aa!=null){
			try{
				byte[] bta=aa.getBytes("iso-8859-1");
				rt=new String(bta, "GBK");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return rt;
	}

	public String toA7String(String aa){
		String rt=null;
		if(aa!=null){
			try{
				byte[] bta=aa.getBytes("GBK");
				rt=new String(bta, "iso-8859-1");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return rt;
	}
%>
 
