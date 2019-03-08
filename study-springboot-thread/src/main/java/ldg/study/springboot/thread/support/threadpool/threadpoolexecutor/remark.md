自定义线程池 ThreadPoolExecutor
    
1、添加任务规则： 详见”CustomThreadPoolExecutor.java“ ->"初始化自定义线程池"

2、销毁线程规则： 详见”CustomThreadPoolExecutor.java“ ->"初始化自定义线程池"

3、饱和拒绝策略

    触发条件：
        taskNum > poolSize + blockingQueue 时，注：poolSize = maxPoolSize
        
    应用分类：
    
        1、丢弃策略
            详见”UnBlockingQueueRejectedExecutionHandler“
            
        2、重入队列策略
            详见”UnBlockingQueueRejectedExecutionHandler“,把任务再次加入到阻塞队列中
            
            **********************************************************************
            不足：导致线程阻塞
            场景：corePoolSize = 5,maxPoolSize = 10,blockingQueue = 3
            原因：当poolSize = 13时，新提交了一个任务，该任务被饱和策略拒绝，该策略把任务再次加入阻塞队列，如果没有空闲的线程或队列时，进入循环，直到有空闲的线程
            结果：
                1、被拒绝的任务容易进行循环
                2、提交任务的线程 阻塞 ”后面的进程”
                    案例：public void test(){ a();   b();}   //a() 提交任务，进入循环后，  b()进行阻塞状态            
            **********************************************************************
    线程池分类：
        1、AbortPolicy 默认策略     丢掉这个任务并且会有任何异常
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                //不做任何处理，直接抛出异常
                throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + e.toString());
            }
        2、DiscardPolicy     丢掉这个任务并且不会有任何异常
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                //就是一个空的方法
            }
        3、DiscardOldestPolicy   队列顺序：队尾进，队头出；丢弃最早进入队列的任务删掉腾出空间，再尝试加入队列
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    //移除队头元素
                    e.getQueue().poll();
                    //再尝试入队
                    e.execute(r);
                }
            }
        4、CallerRunsPolicy  添加到线程池失败，则由主线程执行
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    //直接执行run方法
                    r.run();
                }
            }
        5、自定义
            public class MyRejectPolicy implements RejectedExecutionHandler{
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    //自定义代码   r：待执行的任务   executor：线程池
                }
            }