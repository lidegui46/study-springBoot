package ldg.study.springboot.orm.mybatis.v3.model;

/**
 * @author foursix
 * @since 2017/11/15
 */
public class User {
    private int userId;
    private String companyName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
