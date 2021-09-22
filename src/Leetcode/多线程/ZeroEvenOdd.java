package Leetcode.多线程;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

class ZeroEvenOdd {
    private int n;

    private AtomicInteger inc = new AtomicInteger(1);
    private Map<String,Thread> threadMap = new ConcurrentHashMap<>();
    private volatile boolean isZero = true;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        threadMap.put("zero",Thread.currentThread());
        while (true){
            while (!isZero){
                LockSupport.park();
            }
            printNumber.accept(0);
            isZero = false;
            if (inc.get() % 2 == 0){
                LockSupport.unpark(threadMap.get("odd"));
            }else {
                LockSupport.unpark(threadMap.get("even"));
            }
            if (inc.get() == n){
                break;
            }
        }


    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        threadMap.put("even",Thread.currentThread());
        while (true){
            while (inc.get() % 2 == 0){
                LockSupport.park();
            }
            printNumber.accept(inc.getAndIncrement());
            isZero = true;
            LockSupport.unpark(threadMap.get("zero"));
            if (inc.get() == n || inc.get() == n+1){
                break;
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        threadMap.put("odd",Thread.currentThread());
        while (true){
            while (inc.get() % 2 == 1){
                LockSupport.park();
            }
            printNumber.accept(inc.getAndIncrement());
            isZero = true;
            LockSupport.unpark(threadMap.get("zero"));
            if (inc.get() == n || inc.get() == n+1){
                break;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(2,
                2,
                0L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);
        CompletableFuture.supplyAsync(new NumberSupplier())
    }
    private class NumberSupplier implements Supplier<String>{
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

    }
    public static class NumberIntConsumer implements IntConsumer{
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
            System.out.print(value);
        }

        @Override
        public IntConsumer andThen(IntConsumer after) {
            return null;
        }
    }
}