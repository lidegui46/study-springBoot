package ldg.study.springboot.thread.support;

import ldg.study.springboot.thread.service.ThreadService;
import org.springframework.stereotype.Service;

/**
 * 线程
 *
 * @author foursix
 * @since 2017/12/13
 */
@Service
public class ThreadSupport implements ThreadService {
    /**
     * 当前产品数量
     */
    private volatile int productNum;
    /**
     * 产品最小数量
     */
    private final int PRODUCT_MIN_NUM = 2;
    /**
     * 产品最大数量
     */
    private final int PRODUCT_MAX_NUM = 5;

    @Override
    public synchronized void produce() {
        System.out.println("生产：  productNum:   " + this.productNum + "    ProductMinNum:   " + PRODUCT_MAX_NUM + "   this.productNum <= PRODUCT_MAX_NUM:   " + (this.productNum > PRODUCT_MAX_NUM));
        if (this.productNum > PRODUCT_MAX_NUM) {
            try {
                System.out.println("生产：  产品已满,请稍候再生产，当前   " + this.productNum + "  最大：   " + PRODUCT_MIN_NUM);
                wait();
                System.out.println("生产： 等待通知后，当前   " + this.productNum + "  最大：   " + PRODUCT_MIN_NUM);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            this.productNum++;
            System.out.println("生产：  当前：" + this.productNum + "  最大：" + PRODUCT_MIN_NUM);
            notifyAll();//notifyAll 所有原来在该对象上等待被notify的线程统统退出wait的状态，变成等待该对象上的锁，一旦该对象被解锁，他们就会去竞争。
            //notify();//notify则文明得多他只是选择一个wait状态线程进行通知，并使它获得该对象上的锁，但不惊动其他同样在等待被该对象notify的线程们，当第一个线程运行完毕以后释放对象上的锁此时如果该对象没有再次使用notify语句，则即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，继续处在wait状态，直到这个对象发出一个notify或notifyAll，它们等待的是被notify或notifyAll，而不是锁。
        }
    }

    @Override
    public synchronized void consume() {
        System.out.println("消费：   productNum:   " + this.productNum + "   ProductMinNum:   " + PRODUCT_MIN_NUM + "   this.productNum <= PRODUCT_MIN_NUM:   " + (this.productNum <= PRODUCT_MIN_NUM));
        if (this.productNum <= PRODUCT_MIN_NUM) {
            try {
                System.out.println("消费：   缺货,稍候再取，当前：   " + this.productNum + "   最小：   " + PRODUCT_MIN_NUM);
                wait();
                System.out.println("消费：   等待通知后，当前：   " + this.productNum + "   最小：   " + PRODUCT_MIN_NUM);
                this.consume();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            this.productNum--;
            System.out.println("消费：   当前：   " + this.productNum + "   最大：   " + PRODUCT_MIN_NUM);
            notifyAll();
        }
    }
}
