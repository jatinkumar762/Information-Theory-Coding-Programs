import java.io.*;

public class LZWCompression
{
	String input="",prefix="",word;
	char arr[],ch;
	int d_index=0,i,codeword,match;
	String dict[];
			
	public int in_dictionary(String str)
	{
		int i;
		for(i=1;i<=d_index;i++)
			if(str.equals(dict[i]))
			{
				return i;
			}	
		return -999;
	}
	
	public LZWCompression()
	{
		try
    	{
			System.out.print("Enter any String :");
    		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    		input=br.readLine();
    		arr=input.toCharArray();
  	
    	    dict=new String[input.length()];
    	    
    	    System.out.println("Encoded Output :");
    		i=0;
    		while(i<input.length())
    		{
    		    ch=arr[i];
    			word=prefix+ch;
    			
    			if(in_dictionary(word)!=-999)
    			{
    				prefix=word;
    			}
    			else
    			{
    				if(prefix.equals(""))
    					codeword=0;
    				else
    				    codeword=in_dictionary(prefix);
    				System.out.print(codeword);
    				System.out.println(" :"+ch);
    				dict[++d_index]=word;
    				prefix="";
    			}
    			i++;
    		}
    		if(!prefix.equals(""))
    		{
    			codeword=in_dictionary(prefix);
    			System.out.print(codeword);
				System.out.println(" :"+" ");
    		}
    	    System.out.println("Dictionary Contents :");
    		for(i=1;i<=d_index;i++)
    		{
    			System.out.print(i + ":");
    		    System.out.println(dict[i]);
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
	}
    public static void main(String args[])
    {
    	new LZWCompression(); 	
    }
}
