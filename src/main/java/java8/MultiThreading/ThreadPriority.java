package java8.MultiThreading;

class Task1 implements Runnable
{
	public void run()
	{
		System.out.println("run called....."+Thread.currentThread().getName());
	}

}

class Task2 implements Runnable
{
	public void run()
	{
		System.out.println("run called....."+Thread.currentThread().getName());
	}
	
}

public class ThreadPriority {

	public static void main(String[] args) {

		System.out.println("A thread can referer to the currently executing thread object using : "+Thread.currentThread());
		
		Thread thread1 = new Thread(new Task1());
		thread1.setName("Thread1");
		Thread thread2 = new Thread(new Task2());
		thread2.setName("Thread2");
		
		// setting thread priorities
		thread1.setPriority(Thread.MIN_PRIORITY); // setting low priority
		thread2.setPriority(Thread.MAX_PRIORITY); // setting high priority
		
		thread1.start();
		thread2.start();
		
	}

}
