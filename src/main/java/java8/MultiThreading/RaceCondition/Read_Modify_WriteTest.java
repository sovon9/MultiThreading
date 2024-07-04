package java8.MultiThreading.RaceCondition;

class Counter implements Runnable
{
	public int shared = 0;
	
	public void run()
	{
		for(int i=0; i<2000; i++)
		{
			shared++;
		}
	}

	public int getShared() {
		return shared;
	}
	
}

public class Read_Modify_WriteTest {

	public static void main(String[] args) {

		Counter task = new Counter();
		Thread thread1 = new Thread(task);
		Thread thread2 = new Thread(task);
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("After execution value should be 4000 : "+task.getShared());
		
	}

}
