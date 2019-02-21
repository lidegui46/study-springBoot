package ldg.study.springboot.designPattern.adapter.TongDianPlugin;

/**
 * @author： ldg
 * @create date： 2019/2/21
 */
public class GbThreePluginImpl implements ThreePlugin {
    @Override
    public void tongDian() {
        System.out.println("国标 三 通电");
    }
}
