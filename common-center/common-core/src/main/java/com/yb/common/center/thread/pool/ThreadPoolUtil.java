package com.yb.common.center.thread.pool;

import java.util.concurrent.*;

/**
 * @author yebing
 */
public class ThreadPoolUtil {
    /** 实例化单例 */
    private static ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil();

    /** 核心线程数*/
    private int corePoolSize = Runtime.getRuntime().availableProcessors() +2;

    /** 最大线程数*/
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 8;

    /** 心跳时间 */
    private long keepLiveTime = 2L;

    /** 计划线程delay时间 */
    private long delayTime = 2L;

    /** 线程池失败任务队列（重试机制队列）*/
    LinkedBlockingQueue failureTaskQueue = new LinkedBlockingQueue(100);

    /** 默认使用的线程池 */
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepLiveTime,TimeUnit.SECONDS,failureTaskQueue,new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            failureTaskQueue.offer(r);
        }
    });

    /**
     * 获取当前工具类实例
     * @return
     */
    public static ThreadPoolUtil getInstance(){
        return threadPoolUtil;
    }

    /**
     * 构造函数
     */
    private ThreadPoolUtil(){
        /* 守护线程 */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Runnable take = (Runnable)failureTaskQueue.take();
                    threadPool.execute(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        }, 0, delayTime, TimeUnit.MILLISECONDS);
    }

    public void execute(Runnable runnable){
        threadPool.execute(runnable);
    }

    public Future<?> submit(Runnable runnable){
        Future<?> submit = threadPool.submit(runnable);
        return submit;
    }

    public Future<?> submit(Callable<?> callable){
        Future<?> submit = threadPool.submit(callable);
        return submit;
    }

}
