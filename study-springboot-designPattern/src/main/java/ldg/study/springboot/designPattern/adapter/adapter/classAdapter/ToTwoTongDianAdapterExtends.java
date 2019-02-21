package ldg.study.springboot.designPattern.adapter.adapter.classAdapter;

import ldg.study.springboot.designPattern.adapter.tongDianPlugin.GbThreePluginImpl;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.TwoPlugin;

/**
 * 【继承适配器】“某种” 适配 二线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ToTwoTongDianAdapterExtends extends GbThreePluginImpl implements TwoPlugin {
    @Override
    public void tongDian() {
        System.out.println("【继承式适配器】开始适配了");
        System.out.println("插头 二 通电了");
        super.tongDian();
    }
}
