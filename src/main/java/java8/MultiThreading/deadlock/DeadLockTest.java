package java8.MultiThreading.deadlock;

import java.util.ArrayList;
import java.util.List;

public class DeadLockTest {

	public List<String> resource1;
	public String resource2;
	
//	public DeadLockTest(List<String> data,String index)
//	{
//		this.resource1 = data;
//		this.resource2 = index;
//	}
//	
//	public List<String> getResource1() {
//		return resource1;
//	}
//
//	public void setResource1(List<String> resource1) {
//		this.resource1 = resource1;
//	}
//
//	public String getResource2() {
//		return resource2;
//	}
//
//	public void setResource2(String resource2) {
//		this.resource2 = resource2;
//	}

	public static void main(String[] args) {

		String resource1 = "resource1";
		String resource2 = "resource2";

		//DeadLockTest deadLockTest = new DeadLockTest(list, "Singha");
		
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (resource1) // deadLockTest.getResource1()
				{
					System.out.println("Thread1 aquired the lock on resource1");
					try 
					{
						System.out.println("Thread1 After aquiring lock on "+resource1+" sleeping for 3s");
						Thread.sleep(3000);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread1 woke up");
					synchronized (resource2) // deadLockTest.getResource2()
					{
						System.out.println("Thread1 aquired the lock on resource2");
					}
				}
			}
			
		});
		
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized(resource2)
				{
					System.out.println("Thread2 aquired the lock on resource2");
					try
					{
						System.out.println("Thread2 After aquiring lock on "+resource2+" sleeping for 3s");
						Thread.sleep(3000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread2 woke up");
					synchronized(resource1)
					{
						System.out.println("Thread2 aquired the lock on resource1");
					}
				}
			}
			
		});
		
		thread1.start();
		thread2.start();
		
	}

}
