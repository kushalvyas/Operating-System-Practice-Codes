import java.util.*;
import java.io.*;

class Producer extends Thread{
	static final int MAXQUEUE = 10;
	public Vector stuff = new Vector();

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

	public synchronized void putStuff() throws Exception{
		while(stuff.size() == MAXQUEUE){
			wait();
		}
		stuff.addElement(new Date().toString());
		System.out.println("put message");
		notify();
		sleep(1000);
	}	

	public synchronized String removeStuff() throws Exception{
		notify();
		while(stuff.size() == 0){
			wait();
		}
		sleep(1000);

		String text = stuff.firstElement().toString();
		stuff.removeElement(text);
		return text;
	}
}


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

	public static void main(String[] args) {
		System.out.println("Starting producer and Consumer");
		Producer producer = new Producer();
		producer.start();
		Consumer consumer = new Consumer(producer);
		consumer.start();
	}

}