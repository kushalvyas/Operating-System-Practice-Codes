/*

	This is a code for priority scheduling.. non pre-emptive 
 */

import java.util.*;
import java.io.*;

class priority_non_preemptive  {
	
	public static void main(String[] args) throws IOException {

		// lets make buffered reader
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		// inpput number of jobs
		System.out.println("Enter number of jobs");
		int n = Integer.parseInt(bfr.readLine());
		
		// lets declare an arraylist
		ArrayList<Job> JobArray = new ArrayList<Job>();
		// now lets take input of jobs
		for (int i=0; i<n; i++) {
			System.out.println("Enter job_arrival_time, job_burst_time and priority (space delimeted)");
			String [] temp = bfr.readLine().split(" ");
			int job_arrival_time = Integer.parseInt(temp[0]);
			int job_burst_time = Integer.parseInt(temp[1]);
			int priority = Integer.parseInt(temp[2]);

			JobArray.add(new Job(i, job_arrival_time, job_burst_time, priority));

		}
		// now our list is ready..lets sort it according to priority
		Collections.sort(JobArray, new Comparator<Job>(){
			@Override
			public int compare(Job job1,Job job2){
				return Integer.compare(job1.priority, job2.priority);
			}
		});


		// final sorted list : 
		System.out.println("Sorted list is : ");
		for (Job each : JobArray) {
			System.out.printf("Job id : %d  Job AT : %d Job BT : %d  Job Pr : %d\n",each.job_id,each.job_arrival_time, each.job_burst_time, each.priority);
		}


		// lets display and calculate avg wt time ...
		int sum=0;
		int awt=0;
		int tat=0;
		for(Job each : JobArray){
			System.out.println("Job "+each.job_id+"====="+sum+" - "+(sum+each.job_burst_time));
			awt+=sum-each.job_arrival_time;
			sum+=each.job_burst_time;
			tat+=sum-each.job_arrival_time;
		}

		System.out.println("Average waiting time="+(1.0*awt)/n+"\nAverage turn around time="+(1.0*tat)/n);
		 
	}
}

class Job  {
	public int job_id;
	public int job_arrival_time;
	public int job_burst_time;
	public int priority;

	public Job(int job_id, 
				int job_arrival_time,
				int job_burst_time,
				int priority){
		this.job_id = job_id;
		this.job_arrival_time = job_arrival_time;
		this.job_burst_time = job_burst_time;
		this.priority = priority;
	}
}