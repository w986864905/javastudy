package javastudy.Lambda.syntax;

import javastudy.Lambda.functionalInterfaces.*;
/**
 * @author 王品峰
 * @DateTime 2020/7/20 10:04
 * @Description
 */
public class BasicPro {

    private static interface Calculate{
        int calculate(int a,int b);
    }
    private static int x = 10;
    public static void main(String[] args) {
        //1.实现一个参数，有返回值的函数式接口
        SingleReturnSingleParameter lambda8 = (a)->a+1;
        //System.out.println(lambda8.test(10));
        //Calculate calculate = BasicPro::Calculate;
        Calculate calculate = new BasicPro()::Calculate2;
        //System.out.println(calculate.calculate(10,20));

        NoneReturnNoneParameter lambda0 = ()-> System.out.println("x= " + x);
        lambda0.test();
        x = 20;
        System.out.println(x);
    }
    private static int Calculate(int x, int y){
        return x+y;
    }
    private int Calculate2(int a, int b){
        return a-b;
    }
}
