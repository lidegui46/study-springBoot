package ldg.study.springboot.designPattern.adapter.adapter.objectAdapter;


import ldg.study.springboot.designPattern.adapter.tongDianPlugin.TongDianPlugin;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.TwoPlugin;

/**
 * 【对象适配器】“某种” 适配 二线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ToTwoTongDianAdapter implements TwoPlugin {

    private final TongDianPlugin plugin;

    public ToTwoTongDianAdapter(TongDianPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void tongDian() {
        System.out.println("【适配器】二线插头 适配了");
        plugin.tongDian();
    }
}