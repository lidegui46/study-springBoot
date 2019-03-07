线程池

    阿里建议：
        线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，
        这样的处理方式让开发者更加明确线程池的运行规则，规避资源耗尽的风险
    
        原因：
            Executors.newFixedThreadPool 和 Executors.newSingleThreadPool
                允许的请求队列为Integer.MAX_VALUE，可能会堆积大量的请求，导致OOM
            Executors.newCachedThreadPool 和 Executors.newScheduledTheadPool 
                允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，导致OOM
                
    总结：
        1、问：用ThreadPoolExector自定义线程池，根据任务量使用队列
            任务量不大  ： 可以使用 无界队列 和 有界队列；
            任务量非常大： 要用有界队列，防止出现OOM
            
        2、问：如果任务量很大，还要求每个任务都处理成功
            对任务进行阻塞提交
            拒绝机制改为任务重入队列，以此保证线程最终执行
        
        3、问：线程池最大数量设置多少？
            2N + 1 （N为CPU数量）
           
        4、问：核心线程数怎么设置？
            
            
        5、问：如何获取线程执行结果？
            使用CompletionService
            注意事项：获取任务的结果时建议重新开一个线程获取，如果在主线程获取，就要等任务都提交后才获取，就会阻塞大量任务结果，队列过大OOM，
                所以最好异步开个线程获取结果