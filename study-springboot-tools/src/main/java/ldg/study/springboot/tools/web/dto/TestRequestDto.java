package ldg.study.springboot.tools.web.dto;

import java.io.Serializable;

/**
 * @author： ldg
 * @create date： 2018/7/17
 */
public class TestRequestDto implements Serializable {
    private String name;
    private String group;
    private Integer age;

    /*public TestRequestDto(String name,String group,Integer age){
        this.name = name;
        this.group = group;
        this.age  = age;
    }*/

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
