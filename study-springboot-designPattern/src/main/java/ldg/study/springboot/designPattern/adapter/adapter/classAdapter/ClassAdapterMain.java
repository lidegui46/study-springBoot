package ldg.study.springboot.designPattern.adapter.adapter.classAdapter;

import ldg.study.springboot.designPattern.adapter.noteBook.DellNoteBookImpl;
import ldg.study.springboot.designPattern.adapter.noteBook.MacNoteBookImpl;
import ldg.study.springboot.designPattern.adapter.noteBook.NoteBookPlugin;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.ThreePlugin;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.TwoPlugin;

/**
 * 【类适配器】入口
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ClassAdapterMain {
    public static void main(String[] args) {
        macNoteBook_Three();
        dellNoteBook_Two();
    }

    private static void macNoteBook_Three() {
        //二线插头 适配 三线插头
        //注：插头适配器需实现苹果所需的三线插头接口
        ThreePlugin threePlugin = new ToThreeTongDianAdapterExtends();
        //笔记本初始化时，通过三线插头
        NoteBookPlugin noteBook = new MacNoteBookImpl(threePlugin);
        //充电
        noteBook.chongDian();
    }

    private static void dellNoteBook_Two() {
        //三线插头 适配 二线插头
        //注：插头适配器需实现Dell所需的二线插头接口
        TwoPlugin twoPlugin = new ToTwoTongDianAdapterExtends();
        //笔记本初始化时，通过二线插头
        NoteBookPlugin noteBook = new DellNoteBookImpl(twoPlugin);
        //充电
        noteBook.chongDian();
    }
}
