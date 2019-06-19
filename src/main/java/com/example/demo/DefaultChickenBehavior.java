package com.example.demo;

import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * 鸡的行为
 */
public class DefaultChickenBehavior implements ChickenBehavior {
    private Chicken chicken;

    final Byte aByte = Byte.valueOf("1");

    static  final  int MAX_ENERGY =100;

    DefaultChickenBehavior(Chicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public void eat(int energy) {
        Assert.notNull(chicken, "小鸡未确定");
        synchronized (this.chicken){
            if (this.chicken.energy>=MAX_ENERGY){
                try {
                    this.chicken.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                System.out.println("t");
            }else {
                this.chicken.energy+=energy;
                System.out.println("eat:"+this.chicken.energy);
            }
            this.chicken.notify();
        }

        //System.out.println(this.chicken);
    }

    @Override
    public void layEggs(int needEggCount) {
        Assert.notNull(chicken, "小鸡未确定");
        synchronized (this.chicken){
            if (this.chicken.energy<5){
                try {
                    this.chicken.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                this.chicken.energy-=5;
                this.chicken.eggCount+=1;
                System.out.println("egg:"+this.chicken.eggCount);
            }
            this.chicken.notify();
        }
        //return needEggCount;
    }

    @Override
    public Chicken getChicken() {
        return this.chicken;
    }
}
