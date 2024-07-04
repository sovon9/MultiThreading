package java8.MultiThreading;

class Tasktwo implements Runnable
{
	public void run()
	{
		for(int i=0; i<=10;i++)
		{
			System.out.println(Thread.currentThread().getName()+" = "+i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class DaemonThread {

	public static void main(String[] args) {

		Thread daemeonthread = new Thread(new Tasktwo());
		daemeonthread.setDaemon(true);
		daemeonthread.start();
		// giving a little time to daemeonthread to run
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// stopping main method
		throw new RuntimeException("Main thread : "+ Thread.currentThread().getName() +" got terminated....");
		/*
		 * main thread got terminated because of RuntimeException and Daemon thread-0
		 * terminated immediately as JVm doesn't wait for Daemon thread to complete
		 */
	}

}
