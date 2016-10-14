package com.example.dllo.baidumusic.MyThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/10/12.
 */
public class MyPool {
    private static MyPool myPool;
    private final ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    private MyPool() {

        executorService = Executors.newFixedThreadPool(3);
    }

    public static MyPool getInstance() {
        if (myPool == null) {
            synchronized (MyPool.class) {
                if (myPool == null) {

                    myPool = new MyPool();
                }
            }
        }
        return myPool;
    }

}
