import java.util.LinkedList;
import java.util.Queue;

public class NumberQueue {
  private final Queue<Integer> queue = new LinkedList<>();
  private boolean done = false;

  // Add a number to the queue
  public synchronized void addNumber(int n) throws InterruptedException {
    queue.add(n);
    notify(); // Notify the PrimeChecker that a new number is available
  }

  // Retrieve a number from the queue
  public synchronized int get() throws InterruptedException {
    while (queue.isEmpty() && !done) {
      wait(); // wait for num or check if done
    }
    return queue.isEmpty() ? -1 : queue.poll(); // return -1 if no more numbers
  }

  // Mark the queue as done
  public synchronized void setDone() {
    done = true;
    notifyAll(); // Notify all waiting threads
  }
}
