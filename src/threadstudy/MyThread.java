package threadstudy;

/**
 * 线程创建1.继承Thread类
 * @author wangpinfeng on 2020/7/18.
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0;i < 100;i++){
            System.out.println(getName()+": 正在执行!"+i);
        }
    }

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {

                for (int i = 0; i < 100; i++){
                    System.out.println(getName()+": 正在执行!"+i);
                }
            }
        }.start();
        new MyThread().start();
        for (int i = 0; i < 100; i++){
            System.out.println("main线程: 正在执行!"+i);
        }
    }
}
