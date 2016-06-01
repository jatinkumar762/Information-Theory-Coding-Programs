import java.util.Scanner;

class MinHeapNode
{
	char data;
	int frequency;
	MinHeapNode left,right; 
}

class MinHeap
{
	int size;
	int capacity;
	MinHeapNode array[];
}


public class HuffmanCompression 
{
	MinHeapNode newNode(char data,int freq)
	{
		MinHeapNode temp=new MinHeapNode();
		temp.left=temp.right=null;
		temp.data=data;
		temp.frequency=freq;
		return temp;
	}
	
	boolean isSizeOne(MinHeap minHeap)
	{
		if(minHeap.size==1)
			return true;
		else
			return false;
	}
	
	MinHeap createMinHeap(int size)
	{
		MinHeap minHeap=new MinHeap();
		minHeap.size=0;
		minHeap.capacity=size;
		minHeap.array=new MinHeapNode[size];
		return minHeap;
	}
	
	void minHeapify(MinHeap minHeap,int index)
	{
	    int smallest=index;
	    int left=2*index+1;
	    int right=2*index+2;
	    
	    if( left<minHeap.size && minHeap.array[left].frequency < minHeap.array[smallest].frequency)
	    {
	    	smallest=left;
	    }
	    if( right<minHeap.size && minHeap.array[right].frequency < minHeap.array[smallest].frequency)
	    {
	    	smallest=right;
	    }
		if(smallest!=index)
		{
			MinHeapNode temp=minHeap.array[index];
			minHeap.array[index]=minHeap.array[smallest];
			minHeap.array[smallest]=temp;
			minHeapify(minHeap,smallest);
		}
	}
	
	MinHeapNode extractMin(MinHeap minHeap)
	{
		MinHeapNode temp=minHeap.array[0];
		minHeap.array[0]=minHeap.array[minHeap.size-1];
		--minHeap.size;
		minHeapify(minHeap,0);
		return temp;
	}
	
	void buildMinHeap(MinHeap minHeap)
	{
		int n=minHeap.size-1;
		int i;
		for(i=(n-1)/2;i>=0;--i)
		{
			minHeapify(minHeap,i);
		}
	}
	
	public MinHeap createAndBuildMinHeap(char arr[],int freq[],int size)
	{
		MinHeap minHeap=createMinHeap(size);
		for(int i=0;i<size;i++)
		{
			minHeap.array[i]=newNode(arr[i],freq[i]);
		}
		minHeap.size=size;
		buildMinHeap(minHeap);
		return minHeap;
	}
	
	void insertMinHeap(MinHeap minHeap,MinHeapNode minHeapNode)
	{
		++minHeap.size;
		int i=minHeap.size-1;
		while( i>0 && (minHeapNode.frequency < minHeap.array[(i-1)/2].frequency))
		{
			minHeap.array[i]=minHeap.array[(i-1)/2];
			i=(i-1)/2;
		}
		minHeap.array[i]=minHeapNode;
	}
	
	public MinHeapNode buildHuffmanTree(char arr[],int freq[],int size)
	{
		MinHeapNode top=null,left,right;
		
		MinHeap minHeap=createAndBuildMinHeap(arr,freq,size);
		
		while(!isSizeOne(minHeap))
		{
			left=extractMin(minHeap);
			right=extractMin(minHeap);
			top=newNode('$',left.frequency+right.frequency);
			top.left=left;
			top.right=right;
			insertMinHeap(minHeap,top);
		}
	    return extractMin(minHeap); 
	}
	
    boolean isLeaf(MinHeapNode root)
	{
		return (root.left==null)&&(root.right==null);
	}
	void printArr(int arr[],int n)
	{
		int i;
		for(i=0;i<n;i++)
			System.out.print(arr[i]);
		System.out.println();
	}
	void printCodes(MinHeapNode root,int arr[],int top)
	{
		if(root.left!=null)
		{
		   arr[top]=0;
		   printCodes(root.left,arr,top+1);
		}
		if(root.right!=null)
		{
			arr[top]=1;
			printCodes(root.right,arr,top+1);
		}
		
		if(isLeaf(root))
		{
		   System.out.print(root.data + " ");
		   printArr(arr,top);
		}		
	}
	
    public void HuffmanCodes(char arr[],int freq[],int size)
	{
	   MinHeapNode root=buildHuffmanTree(arr,freq,size);
       int tree[]=new int[100],top=0; 
       printCodes(root,tree,top); 
       
	}
	  
	public HuffmanCompression()
	{
	  char arr[]={'a','b','c','d','e','f'};
      int freq[]={5,9,12,13,16,45};
      int size=arr.length;
      HuffmanCodes(arr,freq,size);

	}
 
   public static void main(String args[])
   {
      new HuffmanCompression();               
   }
}
