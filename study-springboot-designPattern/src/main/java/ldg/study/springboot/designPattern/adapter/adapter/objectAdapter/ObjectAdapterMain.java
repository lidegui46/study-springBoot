package ldg.study.springboot.designPattern.adapter.adapter.objectAdapter;

import ldg.study.springboot.designPattern.adapter.adapter.objectAdapter.ToThreeTongDianAdapter;
import ldg.study.springboot.designPattern.adapter.adapter.objectAdapter.ToTwoTongDianAdapter;
import ldg.study.springboot.designPattern.adapter.noteBook.DellNoteBookImpl;
import ldg.study.springboot.designPattern.adapter.noteBook.MacNoteBookImpl;
import ldg.study.springboot.designPattern.adapter.noteBook.NoteBookPlugin;
import ldg.study.springboot.designPattern.adapter.tongDianPlugin.*;

/**
 * 【对象适配器】入口
 * <pre>
 *     场景：苹果笔记本通过三线插头充电，但是可用通电设备只有二线插头
 *     方案：把三线和二线进行适配
 *     范围：一个适配器只能针对一种类型的业务场景,如：二线适配三线，三线适配四线，不能二线即适配三线，又适配四线
 * </pre>
 *
 * @author： ldg
 * @create date： 2019/2/21
 */
public class ObjectAdapterMain {
    public static void main(String[] args) {
        macNoteBook_Three();
        dellNoteBook_Two();
    }

    /**
     * 三线插头 适配 二线插头
     */
    private static void macNoteBook_Three() {
        //二线插头
        TongDianPlugin twoPlugin = new GbTwoPluginImpl();
        //二线插头 适配 三线插头
        //注：插头适配器需实现苹果所需的三线插头接口
        ThreePlugin threePlugin = new ToThreeTongDianAdapter(twoPlugin);
        //笔记本初始化时，通过三线插头
        NoteBookPlugin noteBook = new MacNoteBookImpl(threePlugin);
        //充电
        noteBook.chongDian();
    }

    /**
     * 二线插头 适配 三线插头
     */
    private static void dellNoteBook_Two() {
        //三线插头
        TongDianPlugin threePlugin = new GbThreePluginImpl();
        //三线插头 适配 二线插头
        //注：插头适配器需实现Dell所需的二线插头接口
        TwoPlugin twoPlugin = new ToTwoTongDianAdapter(threePlugin);
        //笔记本初始化时，通过二线插头
        NoteBookPlugin noteBook = new DellNoteBookImpl(twoPlugin);
        //充电
        noteBook.chongDian();
    }
}
