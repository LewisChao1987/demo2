package com.example.demo;

import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * 鸡行为定义
 */
  public interface ChickenBehavior {


    /**
     * 吃食物
     *
     * @param energy
     */
    void eat(int energy);

    /**
     * 下蛋
     *
     * @param needEggCount 期望下蛋数量
     * @return 实际下蛋数量
     */
    void layEggs(int needEggCount);

    Chicken getChicken();
}

