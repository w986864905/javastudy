package javastudy.lambda.interfaces;

/**
 * @author wangpinfeng on 2020/7/19.
 */
public class Test {
}

@FunctionalInterface
interface Test1{
    void test1();
}
@FunctionalInterface
interface Test2{
    void test2();
    default void test(){}
    static void test1(){}
    String toString();
}