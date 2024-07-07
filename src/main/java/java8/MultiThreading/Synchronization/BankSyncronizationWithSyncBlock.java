package java8.MultiThreading.Synchronization;

public class BankSyncronizationWithSyncBlock {

	private double balance;
	
	public BankSyncronizationWithSyncBlock(double balance)
	{
		this.balance = balance;
	}
	
	public void deposit(double amount)
	{
		double newBalance = 0;
		if(balance >0)
		{
			 newBalance = balance + amount;
			 System.out.println(Thread.currentThread().getName()+" updated the balance : "+newBalance);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		balance = newBalance;
		System.out.println(Thread.currentThread().getName()+" new balance : "+balance);
	}
	
	public void withdraw(double amount) 
	{
		double newBalance = 0;
		if(balance >= amount)
		{
			 newBalance = balance - amount;
			 System.out.println(Thread.currentThread().getName()+" updated the balance : "+newBalance);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		balance = newBalance;
		System.out.println(Thread.currentThread().getName()+" new balance : "+balance);
	}
	
	// ************************ synchronized Blocks ***********************
	public void syncDeposit(double amount) // adding synchronized block to fix the RACE issue
	{
		double newBalance = 0;
		synchronized(this) // takes current object Lock
		{
			if(balance >0)
			{
				 newBalance = balance + amount;
				 System.out.println(Thread.currentThread().getName()+" updated the balance : "+newBalance);
			}
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			balance = newBalance;
			System.out.println(Thread.currentThread().getName()+" new balance : "+balance);
		}
	}
	
	public void syncWithdraw(double amount) // adding synchronized block to fix the RACE issue
	{
		double newBalance = 0;
		synchronized(this) // takes current object Lock
		{
			if(balance >= amount)
			{
				 newBalance = balance - amount;
				 System.out.println(Thread.currentThread().getName()+" updated the balance : "+newBalance);
			}
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			balance = newBalance;
			System.out.println(Thread.currentThread().getName()+" new balance : "+balance);
		}
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static void main(String[] args) {

		BankSyncronizationWithSyncBlock bankSyncronization = new BankSyncronizationWithSyncBlock(100);
		
		Thread thread1 = new Thread(()->{
			bankSyncronization.deposit(50);
		}, "deposit1");
		Thread thread2 = new Thread(()->{
			bankSyncronization.deposit(40);
		}, "deposit2");
		Thread thread3 = new Thread(()->{
			bankSyncronization.withdraw(30);
		}, "withdraw1");
		Thread thread4 = new Thread(()->{
			bankSyncronization.withdraw(50);
		}, "withdraw2");
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 100+50+40-30-50 = 110
		System.out.println("Final balance after all modifications : "+bankSyncronization.getBalance());
		
		System.out.println("********************* using Synchronization block *******************\n");
		/*
		 * resetting the balance
		 */
		bankSyncronization.setBalance(100);
		Thread sthread1 = new Thread(()->{
			bankSyncronization.syncDeposit(50);
		}, "deposit1");
		Thread sthread2 = new Thread(()->{
			bankSyncronization.syncDeposit(40);
		}, "deposit2");
		Thread sthread3 = new Thread(()->{
			bankSyncronization.syncWithdraw(30);
		}, "withdraw1");
		Thread sthread4 = new Thread(()->{
			bankSyncronization.syncWithdraw(50);
		}, "withdraw2");
		
		sthread1.start();
		sthread2.start();
		sthread3.start();
		sthread4.start();
		
		try {
			sthread1.join();
			sthread2.join();
			sthread3.join();
			sthread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Final balance after all modifications with synchronization : "+bankSyncronization.getBalance());
		
	}

}
