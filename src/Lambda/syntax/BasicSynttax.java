package Lambda.syntax;

import Lambda.functionalInterfaces.*;

/**
 * @author 王品峰
 * @DateTime 2020/7/20 9:38
 * @Description Lambda表达式的基础语法
 */
public class BasicSynttax {
    public static void main(String[] args) {
        //1.实现一个无参,无返回值的函数式接口
        NoneReturnNoneParameter lambda1 = ()->{
            System.out.println("这是一个无参，无返回值的方法");
        };
        lambda1.test();
        //2.实现一个参数，无返回值的函数式接口
        NoneReturnSingleParameter lambda2 = (int a)->{
            System.out.println("这是一个参数，无返回值的方法，参数a："+a);
        };
        lambda2.test(10);
        //3.实现多个采纳数，无返回值的函数式接口
        NoneReturnMutipuleParameter lambda3 = (int a, int b)->{
            System.out.println("这是一个多参，无返回值的方法，参数a："+a+" 参数b："+b);
        };
        lambda3.test(10,20);
        //4.实现无参数，有返回值的函数式接口
        SingleReturnNoneParameter lambda5 = ()->{
            System.out.println("一个无参数，又返回值的方法，返回值是："+10);
            return 10;
        };
        System.out.println(lambda5.test());
        //5.实现一个参数，有返回值的函数式接口
        SingleReturnSingleParameter lambda6 = (int b)->{
            System.out.println("这是一个有参，有返回值的方法，放回值是："+20);
            return 20;
        };
        System.out.println(lambda6.test(30));
        //实现多个参数，有返回值的函数式接口
        SingleReturnMutipuleParameter lambda7 = (int a, int b)->{
            System.out.println("这是多个参数，有返回值的方法");
            return a+b;
        };
        System.out.println(lambda7.test(10,20));
    }
}
