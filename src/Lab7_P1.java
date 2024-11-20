import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab7_P1 {
  public static void main(String[] args) {
    System.out.println("Enter a number:");
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    if (n < 0) {
      throw new IllegalArgumentException("Input cannot be below zero.");
    }

    NumberQueue queue = new NumberQueue();
    Thread generator = new Thread(new NumberGenerator(queue, n));
    Thread checker = new Thread(new PrimeChecker(queue));

    generator.start();
    checker.start();
  }
}

class NumberQueue {
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

class NumberGenerator implements Runnable {
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

class PrimeChecker implements Runnable {
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
