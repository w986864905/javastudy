package javastudy.threadstudy;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author wangpinfeng on 2020/7/18.
 */
public class Mythread3 implements Callable<Integer>{
    public static void main(String[] args) {
        Mythread3 mythread3 = new Mythread3();
        FutureTask<Integer> futureTask = new FutureTask<>(mythread3);
        for (int i = 0;i < 30;i++){
            System.out.println(Thread.currentThread().getName() + i);
            if (i == 20){
                new Thread(futureTask,"有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值为: " + futureTask);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (i = 0;i < 30;i++){
            System.out.println("call:"+Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}
