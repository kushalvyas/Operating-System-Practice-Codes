import java.util.*;
import java.io.*;

class lru  {
	
	public static void main(String[] args) throws IOException {
		
		// vectors for computation
		Vector val = new Vector();
		Vector page = new Vector();
		Vector counter = new Vector();

		// set up buffered reader
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter page size");
		int n = Integer.parseInt(bfr.readLine());
		System.out.println("Enter values..delimited by space");
		String [] temp = bfr.readLine().split(" ");
		// now unpack the value into the val vector
		for (int i=0; i<temp.length; i++) {
			val.addElement((int)Integer.parseInt(temp[i])); 		
		} 

		// initialize the counter .. 
		for(int i=0;i<n;i++){
			counter.addElement(0);
		}

		int i=0,j=0,hit=0;
		while(i<val.size()){
			int x = (int)val.firstElement();
			val.remove(0);
			// now do the computations inside the page..
			if(page.contains(x)){
				hit++;
				// increment counter of the corresponding index
				int index = page.indexOf(x);
				// System.out.println("index = "+index);
				int count = (int)counter.get(index);
				count = count +1;
				counter.set(index,count);
				// print that it is a hit
				System.out.print("H ");
			}else{
				System.out.print("M ");
				// now it is for sure a miss...
				// but we need to check if page has to be replaced or just added 
				if(page.size() == n){
					// WRITE LRU REPLACEMENET LOGIC HERE
					// replaces the element at j by x
					int min = (int)getmin(counter);
					// System.out.println("min element and vector = "+min +" ==> "+counter);
					int index = counter.indexOf(min);
					// System.out.println(" alpha index = "+index);
					//returns index of minimum used element in page
					/*
						since we are going from 0 to n... the first occurrence of minelement
						is by default the least recently used...
						as the first occurenece means first entry.. ergo..lru element
					 */
					
					counter.set(index,(int)0);
					page.set(index,x);
					
					
				}else{
					// just add it to any frame
					page.addElement(x);
					
				}
			}
			System.out.println("vector = "+page);
		}
		System.out.println("\nhit  = "+hit);

	}

	private static int getmin(Vector v){
		int i=0;
		int min=0;
		for (i=0; i<v.size(); i++) {
			int temp = (int)v.elementAt(i);
			if (temp < min){
				min = temp;
			}
		}
		return min;
	}
}