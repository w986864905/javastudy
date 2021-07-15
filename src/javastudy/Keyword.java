package javastudy;

import test.Person;

/**
 * @ClassName keyword
 * @Description
 * @Author 王品峰
 * @Date 2021/6/10 17:45
 * @Version 1.0
 */
public class Keyword {
    private static final Integer TEN = 10;
    private static final Person PERSON = new Person("liya",11);

    public static void main(String[] args) {
        Person person = PERSON;
        PERSON.setName("wpf");
        Integer ten = TEN;
        ten = 11;
        System.out.println(PERSON);
    }
}

