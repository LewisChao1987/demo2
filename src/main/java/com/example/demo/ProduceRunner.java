package com.example.demo;

import java.util.concurrent.TimeUnit;

public class ProduceRunner implements Runnable {

    Farm farm;
    ChickenBehavior behavior;
    Chicken chicken;
    boolean stop =false;

    public ProduceRunner(ChickenBehavior behavior, Farm farm) {
        this.farm = farm;
        this.behavior = behavior;
        this.chicken = this.behavior.getChicken();
    }

    @Override
    public void run() {
        while (!stop) {
           boolean b = this.behavior.layEggs();
           if (b){
               System.out.println("will stop"+this.chicken);
               System.out.println(farm.chickens.stream().mapToInt(a->a.eggNum).sum());
               b=true;
           }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
