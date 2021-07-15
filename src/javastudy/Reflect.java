package javastudy;

import test.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName reflect
 * @Description
 * @Author 王品峰
 * @Date 2021/6/15 10:10
 * @Version 1.0
 */
public class Reflect {
    private String value;

    private static String object;

    public Reflect() {
        value = "JavaGuide";
        object = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }


    public static void main(String[] args) throws Exception {
        //获取类 class1与class4不会进行初始化
        Class class1 = Reflect.class;
        Class class2 = Class.forName("javastudy.Reflect");
        Reflect o = new Reflect();
        Class class3 = o.getClass();
        Class class4 = ClassLoader.getSystemClassLoader().loadClass("javastudy.Reflect");

        /**
         * 获取所有类中所有定义的方法
         */
        Method[] methods = class2.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        /**
         * 获取指定方法并调用
         */
        Method publicMethod = class2.getDeclaredMethod("publicMethod", String.class);

        publicMethod.invoke(class2, "JavaGuide");

        /**
         * 获取指定参数并对参数进行修改
         */
        Field field = class2.getDeclaredField("object");
        System.out.println(field.get(o));
        field.setAccessible(true);
        field.set(o,"JavaGuide-wpf");
        System.out.println(field.get(class2));
    }
}
