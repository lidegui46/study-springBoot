package ldg.study.springboot.designPattern.adapter.Adapter;

import ldg.study.springboot.designPattern.adapter.TongDianPlugin.ThreePlugin;
import ldg.study.springboot.designPattern.adapter.TongDianPlugin.TongDianPlugin;

/**
 * “某种” 适配 三线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ToThreeTongDianAdapter implements ThreePlugin {

    private final TongDianPlugin plugin;

    public ToThreeTongDianAdapter(TongDianPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void tongDian() {
        System.out.println("【适配器】三线插头 适配了");
        plugin.tongDian();
    }
}
