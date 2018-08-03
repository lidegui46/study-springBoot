package ldg.study.springboot.orm.mybatis.v3.service;

import ldg.study.springboot.orm.mybatis.v3.model.User;

/**
 * @author foursix
 * @since 2017/11/15
 */
public interface UserService {
    User find();

    void update(User user);
}
