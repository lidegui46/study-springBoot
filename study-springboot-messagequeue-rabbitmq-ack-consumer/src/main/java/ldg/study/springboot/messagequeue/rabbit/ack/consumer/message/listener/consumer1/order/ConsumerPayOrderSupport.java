package ldg.study.springboot.messagequeue.rabbit.ack.consumer.message.listener.consumer1.order;

/**
 * 方法二
 * @RabbitListener 也可用于方法上
 */
//@EnableRabbit
//@RabbitListener(queues = RabbitMqConfig.QUEUE_ORDER_PAY_NAME, containerFactory = "orderContainer")
public class ConsumerPayOrderSupport {

    //@RabbitHandler
    public void consumeMessage(String message) {
        System.out.println("consume execute by annotation :  pay order message " + message);
    }
}

