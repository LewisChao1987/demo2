package com.example.demo;

import lombok.*;
import org.springframework.util.Assert;

import java.net.PortUnreachableException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@NoArgsConstructor
public class Farm {
    List<Chicken> chickens;

    private Integer totalEggs = 0;

    private Integer totalEnergy = 0;

    /**
     * 喂鸡锁
     */
    final Byte feed_locker = Byte.valueOf("0");

    /**
     * 收蛋锁
     */
    final Byte collect_locker = Byte.valueOf("0");

    public Integer getTotalEnergy() {
        synchronized (feed_locker) {
            return totalEnergy;
        }
    }

    public void setTotalEggs(Integer totalEggs) {
        synchronized (totalEggs){
            this.totalEggs = totalEggs;
        }
    }
    public  Integer getTotalEggs(){
        synchronized (totalEggs){
            return  this.totalEggs;
        }
    }

    public void reduceEnergy(int energy) {
        synchronized (feed_locker) {
            this.totalEnergy -= energy;
        }
    }

    public void setTotalEnergy(int totalEnergy) {
        synchronized (feed_locker) {
            this.totalEnergy = totalEnergy;
        }
    }

    public void addEggs() {
        synchronized (collect_locker) {
            this.totalEggs++;
        }
    }

    void initChickens(int chickenCount) {
        this.chickens = new LinkedList<>();
        if (chickenCount <= 0) return;
        for (int i = 0; i < chickenCount; i++) {
            chickens.add(Chicken.builder().id(i + 1).name("小鸡" + (i + 1)).energy(0).eggNum(0).healthIndex(100).build());
        }
    }

    /**
     * 显示当前农场小鸡总数
     */
    void showChickens() {
        chickens.forEach(
                a -> System.out.println(a)
        );
    }

    /**
     * 显示当前农场鸡蛋总数
     */
    void showTotalEggs() {
        synchronized (totalEggs) {
            System.out.println(totalEggs);
        }
    }

    /**
     * 小鸡总能量
     */
    void showChickenTotalEnergy() {
        int totalEnergy = this.chickens.stream().mapToInt(a -> a.energy).sum();
        System.out.println(totalEnergy);
    }

    Farm(List<Chicken> chickens) {
        this.chickens = chickens;
    }


    /**
     * 农场开始经营
     *
     * @param totalEnergy  投放饲料(能量)总量
     * @param chickenCount 饲养小鸡总数
     * @return
     */
    void farmStart(int totalEnergy, int chickenCount) {
        this.totalEnergy = totalEnergy;
        System.out.println(this.getTotalEnergy());
        this.initChickens(chickenCount);
        this.feedChickens();
        this.produceEggs();
        this.showChickenTotalEnergy();
        this.collectEggs();
        System.out.println(this.getTotalEnergy());
        this.showChickens();  /// rtr ytyty  qwqw

    }

    private void feedChickens() {
        int nThreads = this.chickens.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.execute(new FeedRunner(new DefaultChickenBehavior(this.chickens.get(i)), this));
        }
//        try {
//            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void produceEggs() {
        int nThreads = this.chickens.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.execute(new ProduceRunner(new DefaultChickenBehavior(this.chickens.get(i))));
        }
//        try {
//            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void  collectEggs(){
        int nThreads = this.chickens.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads); //自然线程安全类  任务逐个执行
        for (int i = 0; i <nThreads ; i++) {
            executorService.execute(new CollectRunner(new DefaultChickenBehavior(this.chickens.get(i)),this));
        }
        while (true){
            System.out.println("farm has eggs:"+this.getTotalEggs());
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Farm farm = new Farm();
        farm.farmStart(3000, 200);
        farm.showChickens();
    }
}




