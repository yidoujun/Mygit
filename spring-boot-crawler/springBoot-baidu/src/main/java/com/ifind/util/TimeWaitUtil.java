package com.ifind.util;

import java.util.Random;

/**
 * 随机生成一个时间等待工具类
 *
 * @author 易都军
 * @date 2020/3/18 14:18
 *
 */
public class TimeWaitUtil {

    /**
     * 等待区间随机秒数
     *
     */
    public static void waitRandomSeconds() {
        try {
            Thread.sleep(getRandomMillionSeconds());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待区间随机秒数
     * @param rate 秒
     */
    public static void waitRandomSeconds(int rate) {
        try {
            Thread.sleep(getRandomMillionSeconds(rate));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取随机数秒数
     *
     * @param rete 秒
     * @return
     */
    public static int getRandomMillionSeconds(int rete) {
        int half = rete / 2;
        int min = rete - half;
        int max = rete + half;
        Random random = new Random();
        int waitSeconds = random.nextInt(max) % (max - min + 1) + min;
        return waitSeconds * 1000;
    }

    public static int getRandomMillionSeconds(){
        return getRandomMillionSeconds(20);
    }
}
