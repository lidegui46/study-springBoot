package ldg.study.springboot.orm.mybatis.v3.support;

import ldg.study.springboot.orm.mybatis.v3.dao.UserMapper;
import ldg.study.springboot.orm.mybatis.v3.model.User;
import ldg.study.springboot.orm.mybatis.v3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author foursix
 * @since 2017/11/15
 */
@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User find() {
        return userMapper.find();
    }


    /**
     * 事务处理
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(User user){
        //更新数据库的数据
    }
}
