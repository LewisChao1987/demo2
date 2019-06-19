package com.example.demo;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ChickenRuner {
    public static void main(String[] args) {
        Chicken chicken = new Chicken();
        chicken.setId(1);
        chicken.setEnergy(0);
        chicken.setName("xiaoji");

        ChickenBehavior chickenBehavior = new DefaultChickenBehavior(chicken);
        Thread thread = new Thread(new EatThread(chickenBehavior));
        Thread thread1 = new Thread(new LayeggsThread(chickenBehavior));
        Thread thread2 = new Thread(new EatThread(chickenBehavior));

        thread.start();
        thread1.start();
        thread2.start();
    }

}
class  EatThread implements Runnable{

    ChickenBehavior behavior;

    public EatThread(ChickenBehavior behavior){
        this.behavior= behavior;
    }

    @Override
    public void run() {
      while (true){
          behavior.eat(3);
          try {
              TimeUnit.MILLISECONDS.sleep(3000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }

}
class LayeggsThread implements  Runnable {
    ChickenBehavior behavior;

    public LayeggsThread(ChickenBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void run() {
        while (true) {
            this.behavior.layEggs(1);
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}