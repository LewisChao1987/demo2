package com.example.demo;

import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * 鸡的行为
 */
public class DefaultChickenBehavior implements ChickenBehavior {
    Chicken chicken;

    DefaultChickenBehavior(Chicken chicken) {
        this.chicken = chicken;
    }

    @Override
    public void eat(int energy) {
        Assert.notNull(chicken, "小鸡未确定");
        this.chicken.energy += energy;
        //System.out.println(this.chicken);
    }

    @Override
    public int layEggs(int needEggCount) {
        Assert.notNull(chicken, "小鸡未确定");
        return needEggCount;
    }

    @Override
    public Chicken getChicken() {
        return this.chicken;
    }
}
