package algorithms.FizzBuzz;

/**
 * @author lzn
 * @date 2023/03/01 16:11
 * @description
 */
public class FizzBuzz_ThreadYield {

    private int n;
    private volatile int state = 0;

    public FizzBuzz_ThreadYield(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 15 == 0) {
                continue;
            }
            while (state != 3) {
                Thread.yield();
            }
            printFizz.run();
            state = 0;
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 15 == 0) {
                continue;
            }
            while (state != 5) {
                Thread.yield();
            }
            printBuzz.run();
            state = 0;
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            while (state != 15) {
                Thread.yield();
            }
            printFizzBuzz.run();
            state = 0;
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            } else {
                if (i % 3 == 0 && i % 5 == 0) {
                    state = 15;
                } else if (i % 3 == 0) {
                    state = 3;
                } else {
                    state = 5;
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable printFizz = () -> {
            System.out.printf("%s", "fizz\n");
        };
        Runnable printBuzz = () -> {
            System.out.printf("%s", "buzz\n");
        };
        Runnable printFizzBuzz = () -> {
            System.out.printf("%s", "fizzbuzz\n");
        };
        IntConsumer intConsumer = new IntConsumer();
        FizzBuzz_ThreadYield fb = new FizzBuzz_ThreadYield(15);
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
