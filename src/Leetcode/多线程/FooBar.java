package Leetcode.多线程;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;

    private static Semaphore foo = new Semaphore(1);
    private static Semaphore bar = new Semaphore(0);
    private static BlockingQueue<Integer> fooQueue = new LinkedBlockingQueue<Integer>(){{
        add(0);
    }};
    private static BlockingQueue<Integer> barQueue = new LinkedBlockingQueue();
    Map<String,Thread> map = new ConcurrentHashMap<>();
    private volatile boolean fooExec = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        map.put("foo", Thread.currentThread());
        
        for (int i = 0; i < n; i++) {
            while (!fooExec){
                LockSupport.park();
            }
            //foo.acquire();
            //fooQueue.take();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            fooExec = false;
            LockSupport.unpark(map.get("bar"));
        	//barQueue.add(0);
        	//bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        map.put("bar", Thread.currentThread());

        for (int i = 0; i < n; i++) {
            while (fooExec){
                LockSupport.park();
            }
            //bar.acquire();
            //barQueue.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            fooExec = true;
            LockSupport.unpark(map.get("foo"));
            //fooQueue.add(0);
        	//foo.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2,
                2,
                0L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        FooBar fooBar = new FooBar(10);
        CompletableFuture.supplyAsync(()->{
            try {
                fooBar.foo(()->{
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        },executor);
        CompletableFuture.supplyAsync(()->{
            try {
                fooBar.bar(()->{
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        },executor);
        executor.shutdown();
    }

}