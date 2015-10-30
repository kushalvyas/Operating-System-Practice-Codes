import java.util.*;
import java.io.*;


// producer class
class Producer extends Thread{
	static final int MAXQUEUE = 10; // max limit of buffer
	public Vector stuff = new Vector(); // vector to allot data

	@Override
	public void run(){
		try{
			while(true){
				putStuff();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	// producer will put stuff into the vector ... 
	public synchronized void putStuff() throws Exception{
		while(stuff.size() == MAXQUEUE){
			wait();
		}
		stuff.addElement(new Date().toString()); // timepass data to be appended to the vector
		System.out.println("put message");
		notify();
		sleep(1000); // sleep is used to slow down the output
	}	
	// the consumer will remove stuff from the vector
	public synchronized String removeStuff() throws Exception{
		notify();
		while(stuff.size() == 0){
			wait();
		} 
		sleep(1000); // sleep is used to slow down output...

		String text = stuff.firstElement().toString();
		stuff.removeElement(text);
		return text;
	}
}



// consumer class
class Consumer extends Thread{
	Producer producer;
	
	public Consumer(Producer producer){
		this.producer =  producer;
	}

	@Override
	public synchronized void run(){
		try{
			while(true){
				String text = producer.removeStuff();
				System.out.println("Got message" + text);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
}


class producer_consumer extends Thread{

	// main ..
	public static void main(String[] args) {
		System.out.println("Starting producer and Consumer");
		Producer producer = new Producer();
		producer.start();
		Consumer consumer = new Consumer(producer);
		consumer.start();
	}

}