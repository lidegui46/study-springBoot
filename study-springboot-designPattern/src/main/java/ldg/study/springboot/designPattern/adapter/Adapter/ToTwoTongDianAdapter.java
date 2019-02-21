package ldg.study.springboot.designPattern.adapter.Adapter;


import ldg.study.springboot.designPattern.adapter.TongDianPlugin.TongDianPlugin;
import ldg.study.springboot.designPattern.adapter.TongDianPlugin.TwoPlugin;

/**
 * “某种” 适配 三线插头
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
