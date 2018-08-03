package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.listener.consumer1.order;

/**
 * 方法二
 * 注解无效，待测试
 * @RabbitListener 也可用于方法上
 */
//@EnableRabbit
//@RabbitListener(queues = RabbitMqConfig.QUEUE_ORDER_CREATE_NAME, containerFactory = "orderContainer")
public class ConsumerCreateOrderSupport {

    //@RabbitHandler
    public void consumeMessage(String message) {
        System.out.println("consume execute by annotation : create order message " + message);
    }
}

