package ldg.study.springboot.mvc.dto;

import ldg.study.springboot.mvc.customAnnotation.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Request Param Object And Valid Param
 * author：      ldg
 * create date： 2018/4/17
 *
 */
public class UserResponseParam {
    @NotNull(message = "不能为空")
    @NotEmpty(message = "不能为空2")
    private String name;

    @Pattern(regexp = "^1(3|4|5|6|7|8)\\d{9}$", message = "手机号格式错误")
    private String phone;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
