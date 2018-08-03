package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.listener.consumer1.cart;

/**
 * 方法二
 * 注解无效，待测试
 * @RabbitListener 也可用于方法上
 */
//@EnableRabbit
//@RabbitListener(queues = RabbitMqConfig.QUEUE_CART_ADD_NAME, containerFactory = "cartContainer")
public class ConsumerAddCartSupport {

    //@RabbitHandler
    public void consumeMessage(String message) {
        System.out.println("consume execute by annotation : add cart message " + message);
    }
}

