package java8.MultiThreading.RaceCondition;

class BankWithdraw implements Runnable
{
	public int balance = 100;
	
	public void run()
	{
		System.out.println("Waiting for withdraw money : "+Thread.currentThread().getName());
		if(balance>=100)
		{
			System.out.println("allowing withdraw money for : "+Thread.currentThread().getName());
			balance = balance-50;
		}
		else
		{
			System.out.println("withdraw money Not allowed for : "+Thread.currentThread().getName());
		}
		System.out.println("Bank balance after : "+balance);
	}

}

public class CheckThenActTest {

	public static void main(String[] args) {

		BankWithdraw task = new BankWithdraw();
		Thread thread1 = new Thread(task, "Sovon");
		Thread thread2 = new Thread(task, "Sougata");
		
		thread1.start();
		thread2.start();
		/**
		 * Both were allowed to withdraw money since both checked balance >=100 check same time 
		 * Suppose both found balance >=0 and entered inside if and Sougata withdrawn money and balance is 50 but
		 * since Sovon is also allowed it made the balance to 50-50 = 0
		 */

	}

}
