package ldg.study.springboot.tools.model;

/**
 * @author foursix
 * @since 2017/11/16
 */
public class User
{
    private Integer userId;
    private String companyName;
    private String linkMan;

    public User()
    { }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }
}