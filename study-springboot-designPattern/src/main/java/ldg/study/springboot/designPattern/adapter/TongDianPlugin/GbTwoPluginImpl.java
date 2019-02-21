package ldg.study.springboot.designPattern.adapter.TongDianPlugin;

/**
 * 国标 二线
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class GbTwoPluginImpl implements TwoPlugin {
    @Override
    public void tongDian() {
        System.out.println("国标 二线 通电了");
    }
}
