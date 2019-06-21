package com.example.demo;


/**
 * 收鸡蛋的线程
 */
public class CollectRunner implements Runnable {

    Farm farm;
    ChickenBehavior behavior;
    Chicken chicken;

    public CollectRunner(ChickenBehavior behavior, Farm farm) {
        this.farm = farm;
        this.behavior = behavior;
        this.chicken = this.behavior.getChicken();
    }
    @Override
    public void run() {
        while (true){
            synchronized (this.chicken){
                farm.setTotalEggs(farm.getTotalEggs()+this.chicken.getEggNum());
                this.chicken.eggNum=0;
            }
        }

    }
}
