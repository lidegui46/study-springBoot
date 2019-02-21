package ldg.study.springboot.designPattern.adapter.adapter.classAdapter;

import ldg.study.springboot.designPattern.adapter.tongDianPlugin.GbTwoPluginImpl;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.ThreePlugin;

/**
 * 【继承适配器】“某种” 适配 三线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ToThreeTongDianAdapterExtends extends GbTwoPluginImpl implements ThreePlugin {
    @Override
    public void tongDian() {
        System.out.println("【继承式适配器】开始适配了");
        System.out.println("插头 三 通电了");
        super.tongDian();
    }
}
