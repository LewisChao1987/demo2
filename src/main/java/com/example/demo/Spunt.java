package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Spunt {
    private Integer count = 0;

    private final String locker = "";


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
            System.out.println(this.count);
        }
    }
}

class  Test{
    public static void main(String[] args) {
        Spunt spunt = new Spunt();
        spunt.setCount(10000000);
        ExecutorService executorService = Executors.newFixedThreadPool(999);
        for (int i = 0; i <999; i++) {
            executorService.execute(new Consumer(spunt));
        }
        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //executorService.shutdownNow();
        System.out.println(spunt.getCount()+"_______________");
    }
}

class  Consumer  implements Runnable {
    Spunt spunt;

    public Consumer(Spunt spunt) {
        this.spunt = spunt;
    }

    @Override
    public void run() {
        int i = 10000;
        while (i > 0) {
            spunt.countReduce(1);
            i--;
        }
    }
}
