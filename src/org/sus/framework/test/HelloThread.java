package org.sus.framework.test;

public class HelloThread
{
	static int x=1;
	public static void main(String[] args)
	{
		new TestClass(x);
		TestThread t = new TestThread();
		t.start();
		
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<2; j++)
			{
				Runnable runnable = new TestRunnable();
				Thread t1 = new Thread(runnable);
				t1.start();
				new Thread(runnable).start();
			}
		}
		
		// Race condition here, may throw DivideByZeroException
		int z = t.y+1/(x+1);
		System.out.println(z);
	}
	
	static class TestThread extends Thread
	{
		int y;

		public void run()
		{
			x=0;
			y++;
			
			simplyFunction(y);
		}
		
		public void simplyFunction(int a)
		{
			System.out.println("Here at "+a);
		}
	}
	
	static class TestRunnable implements Runnable
	{
		int x;
		public void run()
		{
			x = 1;
			aFunction();
		}
		
		public void aFunction()
		{
			x++;
		}
	}
}