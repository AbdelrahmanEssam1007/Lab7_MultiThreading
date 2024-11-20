public class PrimeChecker implements Runnable {
  private final NumberQueue queue;

  public PrimeChecker(NumberQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (true) {
        int number = queue.get();
        if (number == -1) { // exit if queue is done and empty
          break;
        }
        if (isPrime(number)) {
          System.out.println(number + " is prime");
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private boolean isPrime(int n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
