package com.example.demo;

import org.springframework.web.bind.annotation.PutMapping;

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
            synchronized (farm.locker) {
                if (farm.getTotalEnergy() >= 1) {
                    behavior.eat(1);
                    farm.reduceEnergy(1);
                } else {
                    break;
                }
            }
        }
    }
}
