package com.example.demo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
 public class Chicken {
    /**
     * 小鸡编号
     */
    @Getter @Setter
    Integer id;
    /**
     * 小鸡姓名
     */
    @Getter @Setter
    String name;
    /**
     * 小鸡拥有的能量
     */
    Integer energy;

   /**
    * 小鸡当前拥有的鸡蛋
    */
    Integer eggNum=10;

    /**
     * 小鸡健康指数
     */
    Integer healthIndex=100;
}
