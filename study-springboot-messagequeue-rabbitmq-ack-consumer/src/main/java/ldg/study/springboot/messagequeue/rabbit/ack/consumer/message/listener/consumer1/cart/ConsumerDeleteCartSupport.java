package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.listener.consumer1.cart;

/**
 * 方法二
 * 注解无效，待测试
 *
 * @RabbitListener 也可用于方法上
 */
//@EnableRabbit
//@RabbitListener(queues = RabbitMqConfig.QUEUE_CART_DELETE_NAME, containerFactory = "cartContainer")
public class ConsumerDeleteCartSupport {

    //@RabbitHandler
    public void consumeMessage(String message, String queueName) {
        System.out.println("consume execute by annotation : delete cart message " + message);
        System.out.println(queueName);
    }
}

