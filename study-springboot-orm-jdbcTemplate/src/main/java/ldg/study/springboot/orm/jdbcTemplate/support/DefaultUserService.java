package ldg.study.springboot.orm.jdbcTemplate.support;

import ldg.study.springboot.orm.jdbcTemplate.model.User;
import ldg.study.springboot.orm.jdbcTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author foursix
 * @since 2017/11/16
 */
@Repository
public class DefaultUserService implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public User find() {
        String sql = "select userid,companyName,linkMan from userBasic WHERE userId = ? limit 1;";
        return jdbcTemplate.queryForObject(sql, new Object[]{168}, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findList() {
        String sql = "select userid,companyName,linkMan from userBasic limit 10;";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<User>(User.class));
    }



}
