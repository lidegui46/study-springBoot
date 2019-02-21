package ldg.study.springboot.designPattern.adapter.noteBook;

import ldg.study.springboot.designPattern.adapter.tongDianPlugin.ThreePlugin;

/**
 * 苹果笔记本 ,需要三线插头
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class MacNoteBookImpl implements NoteBookPlugin {

    private ThreePlugin plugin;

    public MacNoteBookImpl(ThreePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void chongDian() {
        //第一步：通电
        plugin.tongDian();
    }
}