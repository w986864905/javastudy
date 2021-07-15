package javastudy.threadstudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockConditionTest
 * @Description 实现数字和字符串交替打印
 * @Author 张东东
 * @Date 2020/12/3 13:32
 * @Version 1.0
 */
public class LockConditionTest {
    private Lock lock = new ReentrantLock(true);
    private Condition numShow = lock.newCondition();
    private Condition strShow = lock.newCondition();
    private volatile int sign = 1;//1打印数字;2打印字符串

    public Runnable numThread() {
        final String[] inputArr = buildNumArr(52);
        return new Runnable() {
            private String[] arr = inputArr;
            public void run() {
                for (int i = 0; i < arr.length; i=i+2) {
                    try {
                        lock.lock();
                        while(sign == 2){
                            numShow.await();
                        }
                        System.out.println(arr[i]+""+arr[i + 1]);
                        sign = 2;
                        strShow.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }
    public Runnable strThread() {
        final String[] inputArr = buildStrArr(26);
        return new Runnable() {
            private String[] arr = inputArr;
            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        lock.lock();
                        while(sign == 1){
                            strShow.await();
                        }
                        System.out.println(arr[i]);
                        sign = 1;
                        numShow.signalAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }
    public static String[] buildNumArr(int max) {
        String[] noArr = new String[max];
        for(int i=0;i<max;i++){
            noArr[i] = Integer.toString(i+1);
        }
        return noArr;
    }

    public static String[] buildStrArr(int max) {
        String[] charArr = new String[max];
        int tmp = 65;
        for(int i=0;i<max;i++){
            charArr[i] = String.valueOf((char)(tmp+i));
        }
        return charArr;
    }

    public static void main(String args[]) throws InterruptedException {
        LockConditionTest two = new LockConditionTest();
        new Thread(two.numThread()).start();
        new Thread(two.strThread()).start();
    }

}
