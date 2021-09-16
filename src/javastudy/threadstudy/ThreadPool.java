package javastudy.threadstudy;

import cn.hutool.core.date.DateTime;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangpinfeng on 2020/7/18.
 */
@Slf4j
public class ThreadPool {

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 8;
    private static final int QUEUE_CAPACITY = 20;
    private static final Long KEEP_ALIVE_TIME = 10L;

    /**线程命名*/
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("thread-no" + "-%d")
            .setDaemon(false).build();


    public static void main(String[] args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                THREAD_FACTORY,
                new ThreadPoolExecutor.AbortPolicy());

        //线程池调用
        for (int i = 1;i <= 10;i++){
            //创建任务
            executor.execute(new Print());
        }
        //异步调用
//        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("==============开始执行异步任务==============");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }, executor);
//        future.thenAccept(e-> System.out.println("==============执行异步任务"+e+"=============="));
        executor.shutdown();

    }
}
class Print implements Runnable{

    private static final ReentrantLock LOCK = new ReentrantLock(true);

    public Print() {
    }

    @Override
    public void run() {
        try{
            System.out.println("============"+Thread.currentThread().getName()+":等待获取锁============");
            LOCK.lock();
            try{
                System.out.println("================"+Thread.currentThread().getName()+":获取锁成功============");
                System.out.println(Thread.currentThread().getName() + " date：" + DateTime.now());
                Thread.sleep(3000);

            }finally {
                LOCK.unlock();
                System.out.println("======================"+Thread.currentThread().getName()+":释放锁成功============");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

