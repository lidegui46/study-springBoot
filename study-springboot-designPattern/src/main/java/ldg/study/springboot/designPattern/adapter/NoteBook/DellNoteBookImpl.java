package ldg.study.springboot.designPattern.adapter.NoteBook;

import ldg.study.springboot.designPattern.adapter.TongDianPlugin.TwoPlugin;

/**
 * Dell笔记本
 * Dell需要二线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class DellNoteBookImpl implements NoteBookPlugin {

    private TwoPlugin plugin;

    public DellNoteBookImpl(TwoPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void chongDian() {
        //第一步：通电
        plugin.tongDian();
    }
}