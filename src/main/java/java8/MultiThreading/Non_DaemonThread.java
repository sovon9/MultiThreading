package java8.MultiThreading;

class TaskOne implements Runnable
{
	public void run()
	{
		for(int i=0; i<10;i++)
		{
			System.out.println(Thread.currentThread().getName());
		}
	}
}

public class Non_DaemonThread {

	public static void main(String[] args) {

		Thread thread = new Thread(new TaskOne());
		thread.start();
		throw new RuntimeException("Main thread "+ Thread.currentThread().getName() +" got terminated....");
		/*
		 * even though main thread got terminated because of RuntimeException userthread/non-daemon thread-0
		 * continued to run
		 */
	}

}
