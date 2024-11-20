import java.util.Random;

public class Lab7_P2 {
  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    Thread deposit = new Thread(new Deposit(account));
    Thread withdraw = new Thread(new Withdraw(account));
    deposit.start();
    withdraw.start();

    try {
      deposit.join();
      withdraw.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("Final balance: " + account.getBalance());
  }
}

