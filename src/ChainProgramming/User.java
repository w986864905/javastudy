package ChainProgramming;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 王品峰
 * @DateTime 2020/7/22 9:49
 * @Description
 */
@Data
@Builder
public class User {
    private String name;
    private Integer age;
    private Integer sex;
    public static String name2;


}
