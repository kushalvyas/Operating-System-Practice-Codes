/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Arrays;


/* Name of the class has to be "Main" only if the class is public. */
class DiscScheduling
{

	final static int INF = 1 << 10;
	public static void main(String[] args)
	{
		int i, op,start;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of tracks:");
		int n = sc.nextInt();
		int tracks[] = new int [n];
		System.out.println("Enter tracks:");
		for(i = 0; i < n; i++)
			tracks[i] = sc.nextInt();
		System.out.println("Enter starting track:");
		start = sc.nextInt();
		System.out.println("Enter option:");
		System.out.println("1. FIFO \n2. SSTF\n3. SCAN UP \n4. C-SCAN UP\n5. SCAN DOWN\n6. C-SCAN DOWN");
		op = sc.nextInt();
		switch(op){
			case 1:	fifo(tracks, start, n);break;
			case 2: sstf(tracks,start, n);break;
			case 3: scan_up(tracks,start,n);break;
			case 4: cscan_up(tracks,start,n);break;
			case 5: scan_down(tracks,start,n);break;
			case 6: cscan_down(tracks,start,n);break;
		}
	}

	
	

	 static void fifo(int[] tracks,int start, int n) {
		int i, previous = start, diff;
		float sum = 0, avg;
		System.out.println("Track\tNo of tracks traversed");
		for(i = 0; i < n; i++){			
			diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);
	}

	 static void sstf(int[] tracks,int start, int n) {
		int i, j, previous = start, min, diff, pos = -1, k = -1;
		boolean visited[] = new boolean[n];
		float sum = 0, avg;
		System.out.println("Track\tNo of tracks traversed");
		for(i = 0; i < n; i++){
			min = INF;
			for(j = 0; j < n; j++){
				if (j == k || visited[j])	continue;
				diff = tracks[j] - previous;
				diff = diff > 0?diff: - diff;
				if(diff < min){
					min = diff;
					pos = j;
				}
			}
			visited[pos] = true;		
			System.out.println(tracks[pos]+"\t"+min);
			sum += min;
			previous = tracks[pos];
			k = pos;
		}
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);		
	}

	 static void scan_up(int[] tracks,int start, int n) {
		Arrays.sort(tracks);
		int i, j, previous = start, min, diff, pos = -1;
		boolean visited[] = new boolean[n];
		float sum = 0, avg;
		for(i = 0; i < n; i++)
			if(tracks[i] > start)	
				break;
		pos = i;
		System.out.println("Track\tNo of tracks traversed");
		for(;i < n; i++)
		{
			diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		for(i = pos - 1; i >= 0; i-- )
		{
				diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);		
	}
	static void scan_down(int[] tracks,int start, int n) {
		Arrays.sort(tracks);
		int i, j, previous = start, min, diff, pos = -1;
		boolean visited[] = new boolean[n];
		float sum = 0, avg;
		for(i = n-1; i >= 0; i--)
			if(tracks[i] < start)	
				break;
		pos = i;
		System.out.println("Track\tNo of tracks traversed");
		for(;i >=0 ; i--)
		{
			diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		for(i = pos + 1; i < n; i++)
		{
				diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);		
	}

	 static void cscan_up(int[] tracks,int start, int n) {
		Arrays.sort(tracks);
		int i, j, previous = start, min, diff, pos = -1;
		boolean visited[] = new boolean[n];
		float sum = 0, avg;
		for(i = 0; i < n; i++)
			if(tracks[i] > start)	
				break;
		pos = i;
		System.out.println("Track\tNo of tracks traversed");
		for(;i < n; i++)
		{
			diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		for(i = 0; i < pos; i++ )
		{
				diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);		
	}
	static void cscan_down(int[] tracks,int start, int n) {
		Arrays.sort(tracks);
		int i, j, previous = start, min, diff, pos = -1;
		boolean visited[] = new boolean[n];
		float sum = 0, avg;
		for(i = n-1; i >= 0; i--)
			if(tracks[i] < start)	
				break;
		pos = i;
		System.out.println("Track\tNo of tracks traversed");
		for(;i >=0 ; i--)
		{
			diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		for(i = n-1; i >= pos + 1; i--)
		{
				diff = tracks[i] - previous;
			diff = diff > 0?diff: - diff;
			System.out.println(tracks[i]+"\t"+diff);
			sum += diff;
			previous = tracks[i];
		}
		
		avg = sum/n;
		System.out.println("Total number of tracks traversed: "+sum+
				"\nAverage: number of tracks traversed: "+avg);
		
	}
	 
}
