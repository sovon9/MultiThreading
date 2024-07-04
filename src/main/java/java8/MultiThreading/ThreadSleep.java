package java8.MultiThreading;

import java.util.concurrent.TimeUnit;

class Task implements Runnable
{
	public void run()
	{
		System.out.println("run called....."+Thread.currentThread().getName());
		display();
	}
	
	public void display()
	{
		System.out.println("displying......"+Thread.currentThread().getName());
		show();
	}
	
	public void show()
	{
		System.out.println("showing......"+Thread.currentThread().getName());
	}
}

public class ThreadSleep {

	public static void main(String[] args) {

		Task task = new Task(); // task the thread will perform
		Thread thread = new Thread(task); // assigned task to thread // NEW state
		thread.start(); // RUNNABLE state
		
			try {
				Thread.sleep(1000); // Timed waiting
				//TimeUnit.SECONDS.sleep(1); // sleep for number of timeunits
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		
		System.out.println("main thread......"+Thread.currentThread().getName());
	}

}
