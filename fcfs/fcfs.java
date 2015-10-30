import java.util.*;
import java.io.*;

class FCFS{

	public static void main(String[] args)throws IOException {

		// init buffer reader .. for taking input
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// no of jobs
		System.out.println("Enter the number of jobs");
		int n = Integer.parseInt(bfr.readLine());


		// createa arraylist containing object of class Job
		ArrayList<Job> JobArray = new ArrayList<Job>();

		// take input ... it may or maynot be orderwise... we'll sort the list later
		for(int i=0;i<n;i++){
			System.out.println("Enter job_arrival_time and job_burst_time: (delimited by a space)");
			
			// take input and split it into 2 parts from the 'space'
			String[] temp = bfr.readLine().split(" ");
			// assign inputs to arrival and burst time
            int job_arrival_time = Integer.parseInt(temp[0]);
            int job_burst_time = Integer.parseInt(temp[1]);
            // append new job to JobArray ka arraylist
			JobArray.add(new Job(i,job_arrival_time, job_burst_time));
		}


		// sorting the list based on arrival time .. 
		Collections.sort(JobArray, new Comparator<Job>(){
			@Override
			public int compare(Job job1, Job job2){
				return Integer.compare(job1.job_arrival_time, job2.job_arrival_time);
			}
		});


		// final output sequence of FCFS
		System.out.println("Final FCFS Sequence is : ");
		int sum=0,avg_wait_time=0,turn_around_time=0;
		for (int i=0;i<JobArray.size() ;i++ ) {
			Job temp = JobArray.get(i);
			System.out.printf("Process : %d\t ======\t Service Time : %d\n",temp.job_id,(sum+temp.job_burst_time));
			avg_wait_time += sum - temp.job_arrival_time ;
			sum+=temp.job_burst_time;
			turn_around_time += sum - temp.job_arrival_time;
			// System.out.println(avg_wait_time +"---"+sum+"---"+turn_around_time);

		}
		System.out.println("Average Waiting Time "+avg_wait_time/(n*1.0)+" And turn_around_time : "+turn_around_time);
	}
}


class Job{
	/*
	
		Job class contains three fields...
		jobid , arrivaltime and bursttime


	 */
	public int job_id;
	public int job_arrival_time;
	public int job_burst_time;

	// constructor
	public Job(int job_id, int job_arrival_time, int job_burst_time){
		this.job_id = job_id;
		this.job_arrival_time = job_arrival_time;
		this.job_burst_time = job_burst_time;
	}


}