import java.io.*;

public class RLEncoding 
{
    public static void main(String args[])
    {
    	int i,j,len,k;
    	String str="";
    	String res="";
    	System.out.println("Enter any string");
    	try
    	{
    		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    		str=br.readLine();
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex);
    	}
    		len=str.length();
    		char arr[]=str.toCharArray();
    		char res_c[]=new char[len];
    		int res_i[]=new int[len];
    		
    	k=0;
    	for(i=0;i<len;i++)
    	{
    		for(j=i+1;j<len&&arr[i]==arr[j];j++);
    		res_c[k]=arr[i];
    		res_i[k]=(j-i);
    		k++;
    		i=j;
    	}
    	
       for(i=0;i<k;i++)
       {
    	   res+=res_c[i];
    	   res+=res_i[i];
    	   //System.out.print(res_c[i]);
    	   //System.out.print(res_i[i]);
       }
       System.out.println(res);
    }
}
