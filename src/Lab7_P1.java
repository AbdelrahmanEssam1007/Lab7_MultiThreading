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

