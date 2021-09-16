package javastudy;

import test.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName CreateObject
 * @Description
 * @Author 王品峰
 * @Date 2021/9/9 16:32
 * @Version 1.0
 */
public class CreateObject {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Person person1 = (Person) Class.forName("Person.class").newInstance();
        Person person2 = (Person) Class.forName("Person.class").getConstructor().newInstance();
        Person person3 = (Person) person.clone();
    }
}
