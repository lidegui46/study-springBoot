package ldg.study.springboot.thread.service;

/**
 * 线程 服务
 *
 * @author foursix
 * @since 2017/12/13
 */
public interface ThreadService {
    /**
     * 生产商品
     */
    void produce();

    /**
     * 消费商品
     */
    void consume();
}
