package ldg.study.springboot.thread.support.thread;

/**
 * 获取线程中的参数
 */
public class SingleThread {
    public static void main(String[] args) throws InterruptedException {
        OnlineShopping thread = new OnlineShopping();
        thread.start(); //启动线程
        System.out.println("线程执行开始了。。。");
        thread.join();  //等待线程结束
        System.out.println("线程 join 完了。。。");

        System.out.println(thread.name);
    }

    static class OnlineShopping extends Thread {
        private String name;

        @Override
        public void run() {
            System.out.println("开始 执行线程了。。。");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            name = "test";
            System.out.println("线程执行结束了。。。");
        }
    }
}
