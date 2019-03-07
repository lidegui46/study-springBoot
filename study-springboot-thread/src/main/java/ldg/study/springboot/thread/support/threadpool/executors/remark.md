Executors线程池
    
    1、Executors.newFixedThreadPool      固定线程数目的线程池
        描述：配置的corePoolSize与maximumPoolSize大小相同，同时使用了一个无界LinkedBlockingQueue存放阻塞任务，
              因此多余的任务将存在再阻塞队列（无界队列可容纳无限量的任务，因此不会拒绝），不会由RejectedExecutionHandler处理 
        方法：
            public static ExecutorService newFixedThreadPool(int nThreads) {
                return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            }
    2、Executors.newSingleThreadPool     只支持一个线程的线程池
        描述：配置corePoolSize=maximumPoolSize=1，无界阻塞队列LinkedBlockingQueue；保证任务由一个线程串行执行 
        
        方法：
            public static ExecutorService newSingleThreadExecutor() {
                return new FinalizableDelegatedExecutorService(
                            new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
                       );
            }            
    3、Executors.newCachedThreadPool     缓冲功能的线程池
        描述：配置corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，keepAliveTime=60s,以及一个无容量的阻塞队列 SynchronousQueue，
             因此任务提交之后，将会创建新的线程执行；线程空闲超过60s将会销毁 
        
        方法：
            public static ExecutorService newCachedThreadPool() {
                return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
            }    
    4、Executors.newScheduledTheadPool   定时功能的线程池
        描述：配置corePoolSize，无界延迟阻塞队列DelayedWorkQueue；
             有意思的是：maximumPoolSize=Integer.MAX_VALUE，由于DelayedWorkQueue是无界队列，所以这个值是没有意义的 
        
        方法：
            public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
                return new ScheduledThreadPoolExecutor(corePoolSize);
            }
            
            public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
                return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
            }
            
            public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
                super(corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.NANOSECONDS, new DelayedWorkQueue(), threadFactory);
            }