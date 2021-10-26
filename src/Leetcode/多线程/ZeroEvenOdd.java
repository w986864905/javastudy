package Leetcode.多线程;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

class ZeroEvenOdd {
    private int n;

//    private AtomicInteger inc = new AtomicInteger(1);
//    private Map<String,Thread> threadMap = new ConcurrentHashMap<>();
//    private volatile boolean isZero = true;


    private Semaphore zero = new Semaphore(1);


    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);


    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
//        threadMap.put("zero",Thread.currentThread());
//        while (true){
//            while (!isZero){
//                LockSupport.park();
//            }
//            printNumber.accept(0);
//            isZero = false;
//            if (inc.get() % 2 == 0){
//                LockSupport.unpark(threadMap.get("even"));
//            }else {
//                LockSupport.unpark(threadMap.get("odd"));
//            }
//            if (inc.get() == n){
//                break;
//            }
//        }
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if(i % 2 == 1){
                // 可以理解为 odd 增加一个信号量，这样 odd 可以继续走流程
                odd.release();
            }else{
                // 可以理解为 even 增加一个信号量， 这样 even 可以继续走流程
                even.release();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
//        if (n != 1) {
//            threadMap.put("even", Thread.currentThread());
//            while (true) {
//                while (inc.get() % 2 == 1 || isZero) {
//                    LockSupport.park();
//                }
//                printNumber.accept(inc.getAndIncrement());
//                isZero = true;
//                LockSupport.unpark(threadMap.get("zero"));
//                if (inc.get() == n || inc.get() == n + 1) {
//                    break;
//                }
//            }
//        }
        for (int i = 2; i <= n; i += 2){

            // 等待信号量，获取到了信号后，往下走
            even.acquire();
            printNumber.accept(i);

            // 发送信号量给 zero
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

//        threadMap.put("odd",Thread.currentThread());
//        while (true){
//            while (inc.get() % 2 == 0 || isZero){
//                LockSupport.park();
//            }
//            printNumber.accept(inc.getAndIncrement());
//            isZero = true;
//            LockSupport.unpark(threadMap.get("zero"));
//            if (inc.get() == n || inc.get() == n+1){
//                break;
//            }
//        }
        for (int i = 1; i <= n; i += 2){

            // 等待信号量，获取到了信号后，往下走
            odd.acquire();
            printNumber.accept(i);

            // 发送信号量给 zero
            zero.release();
        }

    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(3,
                3,
                0L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(1);
        CompletableFuture.supplyAsync(new NumberSupplier(zeroEvenOdd,"zero"),executor);
        CompletableFuture.supplyAsync(new NumberSupplier(zeroEvenOdd,"even"),executor);
        CompletableFuture.supplyAsync(new NumberSupplier(zeroEvenOdd,"odd"),executor);


        executor.shutdown();
    }
    private static class NumberSupplier implements Supplier<String>{
        private ZeroEvenOdd zeroEvenOdd;
        private String methodName;

        public NumberSupplier(ZeroEvenOdd zeroEvenOdd, String methodName) {
            this.zeroEvenOdd = zeroEvenOdd;
            this.methodName = methodName;
        }

        public ZeroEvenOdd getZeroEvenOdd() {
            return zeroEvenOdd;
        }

        public String getMethodName() {
            return methodName;
        }


        @SneakyThrows
        @Override
        public String get() {
            Method method =zeroEvenOdd.getClass().getMethod(methodName,IntConsumer.class);
            method.invoke(zeroEvenOdd,NumberIntConsumer.getInstance());
            return "success";
        }

        static class NumberIntConsumer implements IntConsumer{
            private static IntConsumer instance;

            public static IntConsumer getInstance(){
                if (instance == null){
                    synchronized (NumberIntConsumer.class){
                        if (instance == null){
                            instance = new NumberIntConsumer();
                        }
                    }
                }
                return instance;
            }

            public NumberIntConsumer() {
            }

            @Override
            public void accept(int value) {
                System.out.println(Thread.currentThread().getName()+":"+value);
            }

            @Override
            public IntConsumer andThen(IntConsumer after) {
                return null;
            }
        }

    }

}
