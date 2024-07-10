package java8.MultiThreading.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable
{
	public String taskNo;
	
	public Task(String taskName) {
		super();
		this.taskNo = taskName;
	}

	@Override
	public void run() {
		System.out.println("started task for "+Thread.currentThread().getName()+" and TaskNo: "+taskNo);
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Task ended for "+Thread.currentThread().getName());
	}
	
}


public class ExecutorFrameworkTest {

	public static void main(String[] args) {
		
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		newFixedThreadPool.execute(new Task("1"));
		newFixedThreadPool.execute(new Task("2"));
		newFixedThreadPool.execute(new Task("3"));
		newFixedThreadPool.execute(new Task("4"));
		
		/*
		 * Even though the ExecutorService has finished all the tasks it will not terminate until w call shutdown
		 */
		System.out.println("shutdown called.......");
		newFixedThreadPool.shutdown(); // will execute existing tasks but will not take any new task
		
		// now if existing tasks are taking too long to complete we can wai for a certain time and do force shutdown
		try {
			if(!newFixedThreadPool.awaitTermination(5, TimeUnit.SECONDS))
			{
				System.out.println("doing forceful shutdown"); // we ca see this printing if we reduce the time
				newFixedThreadPool.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//newFixedThreadPool.shutdown();
	}

}
