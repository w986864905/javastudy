package javastudy.threadstudy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangpinfeng on 2020/7/18.
 */
public class ThreadPool {
    private static ExecutorService executor = new ThreadPoolExecutor(10,10,60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));
    public static void main(String[] args) {
        for (int i = 1;i < 10;i++){
            System.out.println("提交第"+i+"个任务");
            //创建任务
            executor.execute(()->{
                try{
                    System.out.println(">>>task is running========");
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        //
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
