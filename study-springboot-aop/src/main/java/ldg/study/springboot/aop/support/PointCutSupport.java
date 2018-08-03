package ldg.study.springboot.aop.support;

import ldg.study.springboot.aop.service.PointCutService;
import org.springframework.stereotype.Service;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
@Service
public class PointCutSupport implements PointCutService {

    @Override
    public String print(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("hello:");
        sb.append(name);

        //test();

        return sb.toString();
    }
    private void test(){
        Integer i=0;
        Integer b = 1;
        int i1 = b / i;
    }
}
