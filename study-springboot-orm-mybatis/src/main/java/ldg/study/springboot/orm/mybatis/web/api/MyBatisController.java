package ldg.study.springboot.orm.mybatis.web.api;

import ldg.study.springboot.orm.mybatis.promotions.model.Coupons;
import ldg.study.springboot.orm.mybatis.promotions.service.CouponService;
import ldg.study.springboot.orm.mybatis.v3.model.User;
import ldg.study.springboot.orm.mybatis.v3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foursix
 * @since 2017/11/15
 */
@RestController
@RequestMapping(value = "/orm/mybatis")
public class MyBatisController {

    @Autowired
    private UserService userService;

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index() {

        User user = userService.find();

        Coupons coupons = couponService.find();

        return "";
    }
}
