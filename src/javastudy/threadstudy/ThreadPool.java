package javastudy.threadstudy;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * @author wangpinfeng on 2020/7/18.
 */
public class ThreadPool {

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 4;
    private static final int QUEUE_CAPACITY = 20;
    private static final Long KEEP_ALIVE_TIME = 10L;

    //线程命名
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("thread-wpf" + "-%d")
            .setDaemon(true).build();


    public static void main(String[] args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        //线程池调用
        for (int i = 1;i <= 20;i++){
            //创建任务
            executor.execute(()->{
                try{
                    System.out.println("CurrentThread name:" + Thread.currentThread().getName() + "date：" + Instant.now());
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
            if (i ==6){
                executor.setCorePoolSize(5);
                executor.setCorePoolSize(10);
            }
        }
        //异步调用
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("==============开始执行异步任务==============");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }, executor);
        future.thenAccept(e-> System.out.println("==============执行异步任务"+e+"=============="));
        executor.shutdown();
    }
}
