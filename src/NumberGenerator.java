public class NumberGenerator implements Runnable {
  private final NumberQueue queue;
  private final int n;

  public NumberGenerator(NumberQueue queue, int n) {
    this.queue = queue;
    this.n = n;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i <= n; i++) {
        queue.addNumber(i);
        System.out.println("Generated: " + i);
      }
      queue.setDone();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
