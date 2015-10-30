import java.util.*;
import java.io.*;


class Philosopher extends Thread{

	public Chopstick left;
	public Chopstick right;

	public String name;

	public int state=0; // by defualt thinking

	public Philosopher( String name, Chopstick left, Chopstick right){
		this.name = name;
		this.left = left;
		this.right = right;
	}

	@Override
	public synchronized void run(){
		while(true){
			try{
				think();
				eat();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

	// when philosopher is thinking...he's doing nothing useful.. so timepass
	public void think() {
		System.out.println("Philosopher is thinking " +name);
		
	}

	// eat
	public void eat(){
		// check whether current chopstick and the next one are being used or no ..
		if(!left.used){
			left.pickup();
			if(!right.used){
				right.pickup();
				System.out.println("Philosopher is eating " +name);
				right.putdown();
				left.putdown();
				// notify();
			}else{
				left.putdown();
			}
		}
		try{
			sleep(1000); // to slowdown output
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


class Chopstick {
	public boolean used=false;
	public String name;

	public Chopstick(String name){
		this.name = name;
	}

	public synchronized  void pickup(){
		this.used = true;
	}

	public synchronized  void putdown(){
		this.used = false;
		System.out.println("Chopstick put down by philosopher "+name);
	}
}


class dining_philosopher extends Thread{
	public static void main(String[] args) {
		
		Chopstick[] chopstick = new Chopstick[5];

		//initlize the chopstick
		for(int i=0; i< chopstick.length; i++){
			chopstick[i] = new Chopstick(" "+i);
		}
		Philosopher[] philosophers = new Philosopher[5];
		
		philosophers[0] = new Philosopher("P: 0  ", chopstick[0], chopstick[1]);
		philosophers[1] = new Philosopher("P: 1  ", chopstick[1], chopstick[2]);
		philosophers[2] = new Philosopher("P: 2  ", chopstick[2], chopstick[3]);
		philosophers[3] = new Philosopher("P: 3  ", chopstick[3], chopstick[4]);
		philosophers[4] = new Philosopher("P: 4  ", chopstick[4], chopstick[0]);

		// starting the threads
		for(int i=0;i<philosophers.length;i++){
			philosophers[i].start();
		}
	}
}
