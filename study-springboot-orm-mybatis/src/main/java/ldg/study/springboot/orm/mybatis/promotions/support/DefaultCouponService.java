package ldg.study.springboot.orm.mybatis.promotions.support;

import ldg.study.springboot.orm.mybatis.promotions.dao.CouponMapper;
import ldg.study.springboot.orm.mybatis.promotions.model.Coupons;
import ldg.study.springboot.orm.mybatis.promotions.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author foursix
 * @since 2017/11/15
 */
@Service
public class DefaultCouponService implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Coupons find() {
        return couponMapper.find();
    }
}
