import java.util.*;
import java.io.*;

class FCFS{

	public static void main(String[] args)throws IOException {

		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the number of jobs");
		int n = Integer.parseInt(bfr.readLine());

		ArrayList<Job> JobArray = new ArrayList<Job>();
		for(int i=0;i<n;i++){
			System.out.println("Enter job_arrival_time and job_burst_time: (delimited by a space)");
			String[] temp = bfr.readLine().split(" ");
            int job_arrival_time = Integer.parseInt(temp[0]);
            int job_burst_time = Integer.parseInt(temp[1]);
			JobArray.add(new Job(i,job_arrival_time, job_burst_time));
		}

		Collections.sort(JobArray, new Comparator<Job>(){
			@Override
			public int compare(Job job1, Job job2){
				return Integer.compare(job1.job_arrival_time, job2.job_arrival_time);
			}
		});


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
	public int job_id;
	public int job_arrival_time;
	public int job_burst_time;
	public Job(int job_id, int job_arrival_time, int job_burst_time){
		this.job_id = job_id;
		this.job_arrival_time = job_arrival_time;
		this.job_burst_time = job_burst_time;
	}


}