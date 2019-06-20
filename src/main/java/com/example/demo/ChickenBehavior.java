package com.example.demo;

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
     * @return 实际下蛋数量
     */
    void layEggs();

    Chicken getChicken();
}

