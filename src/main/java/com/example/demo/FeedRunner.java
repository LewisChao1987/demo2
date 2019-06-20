package com.example.demo;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.concurrent.TimeUnit;

public class FeedRunner implements Runnable {

    Farm farm;
    ChickenBehavior behavior;
    Chicken chicken;

    public FeedRunner(ChickenBehavior behavior, Farm farm) {
        this.farm = farm;
        this.behavior = behavior;
    }

    @Override
    public void run() {
        while (farm.getTotalEnergy() > 0) {
            synchronized (farm.feed_locker) {
                if (farm.getTotalEnergy() >= 1) {
                  boolean b =behavior.eat(1);
                    if (b) {
                        farm.reduceEnergy(1);
                    }
                    System.out.println("totalEnergy:"+farm.getTotalEnergy());
                } else {
                    break;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
