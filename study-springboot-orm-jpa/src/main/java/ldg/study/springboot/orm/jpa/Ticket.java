package ldg.study.springboot.orm.jpa;

/**
 * @author： ldg
 * @create date： 2019/1/14
 */
public class Ticket {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
