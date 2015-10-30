import java.util.*;
import java.io.*;

class fifo {
	
	public static void main(String[] args) throws IOException {

		// declare a vector for storing the values
		Vector val = new Vector();
		Vector page = new Vector();

		// initiate buffered reader
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter number of page frame size");
		int n = Integer.parseInt(bfr.readLine());
		// take input for the page values
		System.out.println("Enter the page values (delimited by space) ");
		String temp = bfr.readLine();
		String [] values = temp.split(" ");
		for (int i=0; i<values.length; i++) {
			int tt = Integer.parseInt(values[i]);
			val.addElement(tt);
		}

		System.out.println("Values obtained in vector are:"+val);


		// for first n elements... just add them
		// for(int i=0;i<n;i++){
		// 	page.addElement((int)val.firstElement());
		// 	val.remove(0);
		// }
		
		int i=0,j=0,hit=0;
		while(i<val.size()){
			// extract value from vector...
			int x = (int)val.firstElement();
			val.remove(0);			// decrements the size as it is ..

			if(page.contains(x)){
				hit++;
				// j = (j+1)%n;
				System.out.print("H ");
			}else{
				System.out.print("M ");
				// now it is for sure a miss...
				// but we need to check if page has to be replaced or just added 
				if(page.size() == n){
					// so we need to replace the page
					// replaces the element at j by x
					page.set(j,x);
					j = (j+1)%n;
				}else{
					// just add it to any frame
					page.addElement(x);
					j = (j+1)%n;
				}
			}

			// System.out.println("Vector : "+page);

		}

		System.out.println("Hit  = " +hit);
		System.out.println("Hit ratio = "+ (hit*1.0)/values.length);
		System.out.println("Miss ratio = "+(1-(hit*1.0)/values.length));
	}
}