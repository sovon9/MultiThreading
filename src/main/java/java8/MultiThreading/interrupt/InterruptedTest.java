package java8.MultiThreading.interrupt;

public class InterruptedTest {

	public static void main(String[] args) {

		Thread thread = new Thread(()->
		{
			System.out.println("Thread1 going to sleep.....");
			try
			{
				Thread.sleep(9000);
			}
			catch (InterruptedException e) {
				System.out.println("====> Thread1 is interrupted.......");
			}
		});
		thread.start();
		
		System.out.println("main thread going to sleep....");
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			System.out.println("Thread1 is interrupted.......");
		}
		System.out.println("interrupting Thread1....");
		thread.interrupt();
		System.out.println("main thread ending......");
	}

}
