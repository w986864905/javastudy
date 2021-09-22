package Leetcode.多线程;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Foo {

//    private Semaphore seamFirstTwo = new Semaphore(0);

//    private Semaphore seamTwoSecond = new Semaphore(0);

    private AtomicInteger firstTwoThree = new AtomicInteger(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {


        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstTwoThree.getAndIncrement();
        //seamFirstTwo.release();

    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (true){
            if (firstTwoThree.get() == 2){
                //seamFirstTwo.acquire();
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                firstTwoThree.getAndIncrement();
                break;
                //seamTwoSecond.release();
            }
        }


    }

    public void third(Runnable printThird) throws InterruptedException {
        while (true) {
            if (firstTwoThree.get() == 3) {
                //seamTwoSecond.acquire();
                // printThird.run() outputs "second". Do not change or remove this line.
                printThird.run();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Long start = System.currentTimeMillis();
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
                    System.out.println(System.currentTimeMillis()-start);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}