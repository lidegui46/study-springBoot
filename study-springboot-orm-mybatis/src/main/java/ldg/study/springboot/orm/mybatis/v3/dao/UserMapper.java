package ldg.study.springboot.orm.mybatis.v3.dao;

import ldg.study.springboot.orm.mybatis.v3.model.User;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;

/**
 * @author foursix
 * @since 2017/11/15
 */
public interface UserMapper {
    User find();

    void finds(@Nullable @Param("p") String p);
}
