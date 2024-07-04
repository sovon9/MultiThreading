package java8.MultiThreading;


public class DaemonThread2 {

	public static void main(String[] args) {

		Thread thread1 = new Thread(()->
		{
			for(int i=0; i<=10;i++)
			{
				System.out.println("Non-Daemon thread : "+" = "+i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		// daemon thread
		Thread daemonThread = new Thread(()->
		{
			for(int i=0; i<=10;i++)
			{
				System.out.println("Daemon thread : "+" = "+i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// setting daemonThread as Daemon thread
		daemonThread.setDaemon(true);
		
		
		thread1.start();
		daemonThread.start();
		/*
		 * Non-Daemon thread able to reach 10 but Daemon thread never reached 10
		 * Because as soon as non-Daemon thread completes it's execution JVm terminates irrespective of 
		 * whether Daemon thread has completed or not
		 */
	}

}
