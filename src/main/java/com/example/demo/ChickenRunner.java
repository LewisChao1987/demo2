package com.example.demo;

import java.util.concurrent.TimeUnit;

public class ChickenRunner {
    public static void main(String[] args) {
        Chicken chicken = new Chicken();
        chicken.setEnergy(0);
        chicken.setEggNum(0);
        chicken.setId(1);
        chicken.setName("xxxx");
        ChickenBehavior chickenBehavior = new DefaultChickenBehavior(chicken);
        Thread thread = new Thread(new EatThread(chickenBehavior));
        Thread thread1 = new Thread(new EggThread(chickenBehavior));
        thread.start();
        thread1.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(chicken.getEnergy());

    }
}

class EatThread implements Runnable {

    ChickenBehavior chickenBehavior;
    boolean stop = false;

    public EatThread(ChickenBehavior chickenBehavior) {
        this.chickenBehavior = chickenBehavior;
    }

    @Override
    public void run() {
        //chickenBehavior
        while (!stop) {
            try {
                chickenBehavior.eat(2);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("eee");
                this.stop = true;
            }
        }
    }
}

class EggThread implements Runnable {

    ChickenBehavior chickenBehavior;

    public EggThread(ChickenBehavior chickenBehavior) {
        this.chickenBehavior = chickenBehavior;
    }

    @Override
    public void run() {
        while (true) {
            chickenBehavior.layEggs();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
