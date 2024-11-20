public class BankAccount {
  private int balance = 0;

  public synchronized void deposit(int amount) {
    balance += amount;
    System.out.println("Deposited: " + amount +" Current balance: " + balance);
  }

  public synchronized void withdraw(int amount) {
    if(balance >= amount) {
      balance -= amount;
      System.out.println("Withdrawn: " + amount + " Current balance: " + balance);
    } else {
      System.out.println("Attempted to withdraw: " + amount + " Current balance: " + balance + " Insufficient funds");
    }
  }

  public synchronized int getBalance() {
    return balance;
  }
}
