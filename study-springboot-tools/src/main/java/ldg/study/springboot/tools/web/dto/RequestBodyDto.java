package ldg.study.springboot.tools.web.dto;

/**
 * @author： ldg
 * @create date： 2018/7/20
 */
public class RequestBodyDto {
    private String name;
    private String group;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
