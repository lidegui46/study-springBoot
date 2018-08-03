package ldg.study.springboot.orm.jdbcTemplate.service;

import ldg.study.springboot.orm.jdbcTemplate.model.User;

import java.util.List;

/**
 * @author foursix
 * @since 2017/11/16
 */
public interface UserService {
    User find();

    List<User> findList();
}
