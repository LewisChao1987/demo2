package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Spunt {
    private Integer count = 0;

    public final String locker = "";


    public Integer getCount() {
        synchronized (locker) {
            return count;
        }
    }


    public void setCount(Integer count) {
        synchronized (locker) {
            this.count = count;
        }

    }

    public synchronized void countReduce(Integer count) {
        synchronized (locker) {
            this.count -= count;
            //System.out.println(this.count);
        }
    }
}

class Test {
    public static void main(String[] args) {
        final String sx;
        Spunt spunt = new Spunt();
        String lock2 = spunt.locker;
        Thread.interrupted();
//        spunt.setCount(100);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            executorService.execute(new Consumer(spunt));
        }
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ttt");
        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        System.out.println(spunt.getCount() + "_______________");
    }
}

class Consumer implements Runnable {
    Spunt spunt;

    public Consumer(Spunt spunt) {
        this.spunt = spunt;
    }

    @Override
    public void run() {
        int i = 800;
        while (i > 0) {
            spunt.countReduce(1);
            i--;
            if (i <= 50) {
//                break;
                Thread.interrupted();
            }
        }
    }
}
