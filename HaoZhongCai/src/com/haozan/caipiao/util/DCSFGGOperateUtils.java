package com.haozan.caipiao.util;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
/**
 * 单场胜负过关计算注数和金额工具类
 * 
 * @create-time 2014-6-25 16:08 p.m.
 */
public class DCSFGGOperateUtils {

    // / <summary>
    // / 根据玩法获取过关的详细关数
    // / </summary>
    // / <returns></returns>
    public static String getPlayLevelByPlayMethod(int playMethode) {
        String ResultStr = null;
        switch (playMethode) {
            case 301:
                ResultStr = "3";
                break;
            case 401:
                ResultStr = "4";
                break;
            case 501:
                ResultStr = "5";
                break;
            case 601:
                ResultStr = "6";
                break;
            case 701:
                ResultStr = "7";
                break;
            case 801:
                ResultStr = "8";
                break;
            case 901:
                ResultStr = "9";
                break;
            case 1001:
                ResultStr = "10";
                break;
            case 1101:
                ResultStr = "11";
                break;
            case 1201:
                ResultStr = "12";
                break;
            case 1301:
                ResultStr = "13";
                break;
            case 1401:
                ResultStr = "14";
                break;
            case 1501:
                ResultStr = "15";
                break;
            
        }
        return ResultStr;
    }

    /**
     * 获取竞彩足球注数
     * 
     * @param playMethod 玩法，比如201&301
     * @param ticketCode 投注格式
     * @return 投注注数
     */
    public static int getZhushu(int playMethod, String ticketCode) {
        String[] plays = getPlayLevelByPlayMethod(playMethod).split("&"); // 根据玩法判断要过那些关
        String[] codes = ticketCode.split("\\|");
        int length = codes.length;
        int[] codeLength = new int[length];
        for (int i = 0; i < length; i++) {
            codeLength[i] = codes[i].split(",").length;
        }

        int totalCount = 0;// 总共注数
        for (int i = 0; i < plays.length; i++) {
            if (codes.length == Integer.valueOf(plays[i])) {
                int chengshu = 1;
                for (int j = 0; j < codes.length; j++) {
                    chengshu *= codes[j].split(",").length;
                }
                totalCount += chengshu;
            }
            else {
                int[] num = new int[codes.length];
                for (int j = 0; j < num.length; j++) {
                    num[j] = j;
                }
                String nnn = "";
                for(int nums = 0 ; nums < num.length ; nums++){
                	nnn+= num[i]+"";
                }
				Log.i("log", nnn + " " + Integer.valueOf(plays[i]));
                List game = combine(num, Integer.valueOf(plays[i]));
                int length1 = game.size();
                for (int k = 0; k < length1; k++) {
                    int chengshu = 1;
                    int[] a = (int[]) game.get(k);
                    int length2 = a.length;
                    for (int j = 0; j < length2; j++) {
                        chengshu *= codeLength[a[j]];
                    }
                    totalCount += chengshu;
                }
            }
        }

        return totalCount;
    }

    /**
     * 获取竞彩足球最高奖金
     * 
     * @param playMethod 玩法，比如201&301
     * @param spArray 赔率数组
     * @return 最高奖金
     */
    public static float getBiggestAward(int playMethod, String spArray) {
        String[] plays = getPlayLevelByPlayMethod(playMethod).split("&"); // 根据玩法判断要过那些关
        String[] sp = spArray.split("\\|");
        int length = sp.length;
        float[] spNum = new float[length];
        for (int i = 0; i < length; i++) {
            spNum[i] = Float.valueOf(sp[i]);
        }

        float totalAward = 0;// 总奖金
        for (int i = 0; i < plays.length; i++) {
            if (sp.length == Integer.valueOf(plays[i])) {
                float chengshu = 1;
                for (int j = 0; j < sp.length; j++) {
                    chengshu *= spNum[j];
                }
                totalAward += chengshu;
            }
            else {
                int[] num = new int[sp.length];
                for (int j = 0; j < num.length; j++) {
                    num[j] = j;
                }
                List game = combine(num, Integer.valueOf(plays[i]));
                int length1 = game.size();
                for (int k = 0; k < length1; k++) {
                    float chengshu = 1;
                    int[] a = (int[]) game.get(k);
                    int length2 = a.length;
                    for (int j = 0; j < length2; j++) {
                        chengshu *= spNum[a[j]];
                    }
                    totalAward += chengshu;
                }
            }
        }
        totalAward *= 2;
        return totalAward;
    }

    /**
     * 获取对应组合
     * 
     * @param a 组合数组，比如{0,1,2,3,4,5}
     * @param m 组合选中数，比如2
     * @return 返回组合队列，比如0,1 0,2等所有5组合2队列
     */
    public static List combine(int[] a, int m) {
        int n = a.length;

        List result = new ArrayList();

        int[] bs = new int[n];
        for (int i = 0; i < n; i++) {
            bs[i] = 0;
        }
        // 初始化
        for (int i = 0; i < m; i++) {
            bs[i] = 1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos = 0;
        int sum = 0;
        // 首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(print(bs, a, m));

            for (int i = 0; i < n - 1; i++) {
                if (bs[i] == 1 && bs[i + 1] == 0) {
                    bs[i] = 0;
                    bs[i + 1] = 1;
                    pos = i;
                    break;
                }
            }
            // 将左边的1全部移动到数组的最左边

            for (int i = 0; i < pos; i++) {
                if (bs[i] == 1) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = 1;
                }
                else {
                    bs[i] = 0;
                }
            }

            // 检查是否所有的1都移动到了最右边
            for (int i = n - m; i < n; i++) {
                if (bs[i] == 0) {
                    tempFlag = false;
                    break;
                }
            }
            if (tempFlag == false) {
                flag = true;
            }
            else {
                flag = false;
            }

        }
        while (flag);
        result.add(print(bs, a, m));

        return result;
    }

    private static int[] print(int[] bs, int[] a, int m) {
        int[] result = new int[m];
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result[pos] = a[i];
                pos++;
            }
        }
        return result;
    }
}
