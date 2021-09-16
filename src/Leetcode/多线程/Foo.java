package Leetcode.多线程;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Foo {

    private Semaphore seamFirstTwo = new Semaphore(0);

    private Semaphore seamTwoSecond = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {


        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        seamFirstTwo.release();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        seamFirstTwo.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        seamTwoSecond.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        seamTwoSecond.acquire();
        // printThird.run() outputs "second". Do not change or remove this line.
        printThird.run();

    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.first(() -> {
                    System.out.print("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(() -> {
                    System.out.print("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> {
                    System.out.print("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}