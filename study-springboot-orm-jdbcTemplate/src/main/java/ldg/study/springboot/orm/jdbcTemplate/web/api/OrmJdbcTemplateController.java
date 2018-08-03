package ldg.study.springboot.orm.jdbcTemplate.web.api;

import ldg.study.springboot.orm.jdbcTemplate.model.User;
import ldg.study.springboot.orm.jdbcTemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author foursix
 * @since 2017/11/10
 */
@RestController
@RequestMapping(value = "/ormJdbc")
public class OrmJdbcTemplateController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index() {
        User user = userService.find();

        List<User> list = userService.findList();

        return "";
    }
}
