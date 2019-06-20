package com.example.demo;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 鸡的行为
 */
public class DefaultChickenBehavior implements ChickenBehavior {
    Chicken chicken;

    static final int MAX_ENERGY = 15;  //小鸡 持有最大能量
    static final int MIN_ENERGY = 5;   //小鸡持有最小能量
    static final int ENERGY_TO_EGG_THRESHOLD = 1;  //能量转化为鸡蛋的阈值
    static final int HEALTH_INDEX_THRESHOLD = 50;//小鸡健康指数的阈值  小于阈值 小鸡将不能正常生产

    DefaultChickenBehavior(Chicken chicken) {
        Assert.isTrue((MIN_ENERGY+ENERGY_TO_EGG_THRESHOLD)<MAX_ENERGY,"常量定义不合法");
        this.chicken = chicken;
    }

    @Override
    public boolean eat(int energy) {
        Assert.notNull(chicken, "小鸡未确定");
        synchronized (this.chicken) {
            if (this.chicken.energy >= MAX_ENERGY) {
                try {
                    this.chicken.wait();
                    System.out.println("continue，chickenId"+this.chicken.id);
//                    this.chicken.energy += energy;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    return false;
                }
            } else {

                this.chicken.energy += energy;
                System.out.println("eating....." + this.chicken);

            }
            this.chicken.notify();
            return true;
        }
    }

    @Override
    public boolean layEggs() {
        Assert.notNull(chicken, "小鸡未确定");
        synchronized (this.chicken) {
            //这里保证小鸡产蛋后能量不能小于最小能量
            if (this.chicken.energy <= (MIN_ENERGY + ENERGY_TO_EGG_THRESHOLD)) {
                try {
                    this.chicken.wait();
                    System.out.println("continue laying");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    return false;
                }
            } else {
                this.chicken.energy -= ENERGY_TO_EGG_THRESHOLD;
                this.chicken.eggNum++;
                System.out.println("layied eggs");
            }
            this.chicken.notify();
            return true;
        }
    }

    @Override
    public Chicken getChicken() {
        return this.chicken;
    }

    public static void main(String[] args) {

    }
}
