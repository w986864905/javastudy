package javastudy.threadstudy;

/**
 * @author wangpinfeng on 2020/7/17.
 */
public class MyThread2 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 100; i++){
                    System.out.println("我的线程：正在执行!"+i);
                }
            }
        }).start();
        for (int i = 0;i < 100;i++){
            System.out.println("主线程："+i);
        }
    }
}