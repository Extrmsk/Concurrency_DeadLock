package concurrency;

public class Account {
	
	// private volatile int balance
	private int balance;
	
	public Account(int initBalance) {
		this.balance = initBalance;
	}
	
	public void withdraw(int amount) {
		balance -= amount;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}
	
	public int getBalance() {
		return balance;
	}

}
