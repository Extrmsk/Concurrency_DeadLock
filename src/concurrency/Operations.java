package concurrency;

import javax.naming.InsufficientResourcesException;

public class Operations {
	
	public static void main(String[] args) throws InsufficientResourcesException, InterruptedException {
		final Account a = new Account(1000);
		final Account b = new Account(2000);
		
		System.out.println("a=" + a.getBalance() + "b=" + b.getBalance());
		
		//Tread1
		new Thread(new Runnable() {

			public void run() {
				try {
					transfer (a, b, 500);
				} catch (InsufficientResourcesException | InterruptedException e) {
 					e.printStackTrace();
				}
			}
		}).start();
		
		//Tread2
		transfer(b, a, 300);
	}
	
	static void transfer(Account acc1, Account acc2, int amount) throws InsufficientResourcesException, InterruptedException {
		if (acc1.getBalance() < amount) {
			throw new InsufficientResourcesException();
		}
		
		synchronized (acc1) {
			System.out.println("sync1");
			Thread.sleep(1000);
			synchronized (acc2) {
				System.out.println("sync2");
				acc1.withdraw(amount);
				acc2.deposit(amount);
			}
		}
		
		System.out.println("Transfer success");
		System.out.println("a=" + acc1.getBalance() + "b=" + acc2.getBalance());
	}

}
