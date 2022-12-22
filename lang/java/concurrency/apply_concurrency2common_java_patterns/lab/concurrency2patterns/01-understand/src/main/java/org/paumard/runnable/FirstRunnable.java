package org.paumard.runnable;

public class FirstRunnable {

	public static void main(String[] args) {
		System.out.println("in main the current thread id:" + Thread.currentThread().getId());
		/*
		//java 7 pattern
		Runnable runable = new Runnable(){
			public void run(){
				String name = Thread.currentThread.getName();
				System.out.println("I am running in thread " + name);
			}
		}*/

		//java 8 pattern
		Runnable runnableTask = () -> {
			System.out.println("In task a thread is running in " + Thread.currentThread().getName()
					+ " with id: " + Thread.currentThread().getId() );
		};
		
		Thread t = new Thread(runnableTask);
		t.setName("My thread");
		
		t.start();
		// t.run(); // NO NO NO!!! will execute the task in the same thread
	}
}
