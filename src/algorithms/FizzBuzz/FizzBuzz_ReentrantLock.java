package algorithms.FizzBuzz;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lzn
 * @date 2023/03/01 17:31
 * @Description
 */
public class FizzBuzz_ReentrantLock {

    private int n;
    private final ReentrantLock lock;
    private volatile int index;

    public FizzBuzz_ReentrantLock(int n) {
        this.n = n;
        lock = new ReentrantLock();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (index <= n) {
            lock.lock();
            try {
                if(index <= n) {
                    if (index % 3 == 0 && index % 5 != 0) {
                        printFizz.run();
                        index++;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (index <= n) {
            lock.lock();
            try {
                if(index <= n) {
                    if (index % 5 == 0 && index % 3 != 0) {
                        printBuzz.run();
                        index++;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (index <= n) {
            lock.lock();
            try {
                if(index <= n) {
                    if (index % 3 == 0 && index % 5 == 0) {
                        printFizzBuzz.run();
                        index++;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (index <= n) {
            lock.lock();
            try {
                if(index <= n) {
                    if (index % 3 != 0 && index % 5 != 0) {
                        printNumber.accept(index);
                        index++;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Runnable printFizz = () -> {
            System.out.printf("%s", "fizz");
        };
        Runnable printBuzz = () -> {
            System.out.printf("%s", "buzz");
        };
        Runnable printFizzBuzz = () -> {
            System.out.printf("%s", "fizzbuzz");
        };
        IntConsumer intConsumer = new IntConsumer();
        FizzBuzz_ReentrantLock fb = new FizzBuzz_ReentrantLock(15);
        new Thread(() -> {
            try {
                fb.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fb.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
