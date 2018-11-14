1、CountDownLatch
    
    词义： 计数器
    
    重用性：否
    
    场景: 任务A需要等待其它4个任务执行完成后才能执行
    
    例子：   
        CountDownLatch countDownLatch = new CountDownLatch(4);
        new Thread(){
            //doing 任务
            countDownLatch.countDown(); // "正在执行的任务数量“减1
        };
        countDownLatch.await(); // 线程会被挂起，等待直到"正在执行的任务数量“为0才继续执行A任务
        A();
2、CyclicBarrier
    
    词义： 回环栅栏
    
    重用性：是
    
    场景: 一组线程等待所有线程await()之后，所有线程再同时执行await()之后的任务，
    
    例子：   

3、Semaphore
    
    词义： 信号量
    
    场景: Semaphore可以控同时访问的线程个数
         acquire()： 获取一个许可，如果没有就等待
         acquire(int permits)： 获取permits个许可
        
         release()： 释放一个许可，注意：在释放许可之前，必须先获获得许可。
         release(int permits)：释放permits个许可，注意：在释放许可之前，必须先获获得许可。
         
         availablePermits()：获取可用的许可数目
    
    例子： 

4、总结

    1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
          
        CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
        
        CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
        
        另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

    2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。    