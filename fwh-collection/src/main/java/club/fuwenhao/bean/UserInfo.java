package club.fuwenhao.bean;

import lombok.Data;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/11 11:21 上午
 */
@Data
public class UserInfo {
    private String name;
    private Integer age;
    public String school;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getSchool() {
        return school;
    }
}