package test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 王品峰
 * @DateTime 2020/8/29 17:05
 * @Description
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
