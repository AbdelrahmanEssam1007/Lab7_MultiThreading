import java.util.Random;

public class Withdraw implements Runnable {
  private final BankAccount account;
  private final Random random = new Random();

  public Withdraw(BankAccount account) {
    this.account = account;
  }

  @Override
  public void run() {
    for(int i = 0; i < 10; i++) {
      int amount = 10 + random.nextInt(91);
      account.withdraw(amount);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
