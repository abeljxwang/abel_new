package org.abel.service.soa.message;

public class MetaDesc {
	
	String key,dfvalue,cname,desc;
	int length=10,rtp_time=1;
	String type="S";
	boolean isSubElement=false;
	
	
	public void setName(String nm){key=nm; }
	public String getName(){ return key; }

	public void setCN_Name(String nm){cname=nm; }
	public String getCN_Name(){ return cname; }	

	public void setType(String t){type=t; }
	public String getType(){ return type; }	
	
	public void setLen(int l){length=l; }
	public int getLen(){ return length; }	

	public void setRpt(int l){rtp_time=l; }
	public int getRpt(){ return rtp_time; }	

	
	public void setDesc(String l){desc=l; }
	public String getDesc(){ return desc; }	

	public void setDF_Value(String l){dfvalue=l; }
	public String getDF_Value(){ return dfvalue; }	
	
	public void setSubElementFlag(boolean flag){isSubElement=flag; }
	public boolean isSubEle(){ return isSubElement; }		

	public String toString() {

		return "{"+key+", "+cname+","+ desc +","+ length+", "+rtp_time+", "+type+", "+isSubElement +"}";
	}
	
	//	String key,dfvalue,cname,desc;length,rtp_time,type,isSubElement;
}
