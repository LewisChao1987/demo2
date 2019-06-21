package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * 小鸡产蛋类
 */
public class ProduceRunner implements Runnable {

    ChickenBehavior behavior;
    Chicken chicken;
    boolean stop =false;

    public ProduceRunner(ChickenBehavior behavior) {
        this.behavior = behavior;
        this.chicken = this.behavior.getChicken();
    }

    @Override
    public void run() {
        while (!stop) {
           boolean b = this.behavior.layEggs();
           if (b){
              // System.out.println("will stop"+this.chicken);
               b=true;
           }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
