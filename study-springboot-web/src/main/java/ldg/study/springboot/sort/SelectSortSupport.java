package ldg.study.springboot.sort;

/**
 * 选择排序法
 *
 * @author foursix
 * @since 2018/1/4
 */
public class SelectSortSupport {
    //基本原理：
    // 括第一个记录以外的其他记录进行第二次比较，得到最小的记录并与第二个记录进行位置交换；
    // 重复该过程，直到进行比较的记录只有一个为止。


    public static void sort_asc(int[] array) {
        if (array != null && array.length > 0) {
            int i, j;
            int tmp;
            for (i = 0; i < array.length; i++) {
                for (j = i + 1; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        tmp = array[j];
                        array[j] = array[i];
                        array[i] = tmp;
                    }
                }
            }
        }
    }

    public static void sort_desc(int[] array) {
        if (array != null && array.length > 0) {
            int i, j;
            int tmp;
            for (i = 0; i < array.length; i++) {
                for (j = i + 1; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        tmp = array[j];
                        array[j] = array[i];
                        array[i] = tmp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 9, 6, 7, 2, 8, 4, 3};
        //SelectSortSupport.sort_asc(arr);
        SelectSortSupport.sort_desc(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
