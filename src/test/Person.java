package test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 王品峰
 * @DateTime 2020/8/29 17:05
 * @Description
 */

public class Person implements Cloneable{
    public static Integer id;
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name == null ? null : name.trim();
        this.age = age;

    }

    public String getName() {
        return name == null ? null : name.trim();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
