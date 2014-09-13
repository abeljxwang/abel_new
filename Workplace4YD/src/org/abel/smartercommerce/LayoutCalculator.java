package org.abel.smartercommerce;

public class LayoutCalculator {
	
	public static void main(String[] arg){
		int sizecol=5;
		String[] componentsin={"C11","C12","C21","C11","C11","C11","C11"};
		String[] componentsintoslot;
		int[][] pisitions2D=new int[2][5];
		for(int i=0;i<sizecol;i++){
			pisitions2D[0][i]=0;
			pisitions2D[1][i]=0;			
		}
		String atmp=null;
		String w_tmp=null;
		String h_tmp=null;
		int size_w=0;
		int size_h=0;
		int size_all=0;
		for(int j=0;j<componentsin.length;j++){
			atmp=componentsin[j];
			w_tmp=atmp.substring(1,2);
			h_tmp=atmp.substring(2,3);
			size_w=Integer.valueOf(w_tmp);
			size_h=Integer.valueOf(h_tmp);
			System.out.println("No:"+j+"   "+atmp+"  size:"+size_w+"|"+size_h);
			int m=size_all;
			
			if ((m+size_w)<=sizecol) {
					pisitions2D[0][m]=1;
					if(size_h==2)pisitions2D[1][m]=2;
					m=m+size_w;
					size_all=size_all+size_w;
				}
			else{
				
			}
			
			//size_all=size_all+size_w
			
						
		}
		for(int i=0;i<5;i++){
			System.out.print(pisitions2D[0][i]+"  ");
		}
		System.out.println("");
		for(int i=0;i<5;i++){
			System.out.print(pisitions2D[1][i]+"  ");
			
		}
		
	}

}
