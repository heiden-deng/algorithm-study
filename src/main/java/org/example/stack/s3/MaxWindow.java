package org.example.stack.s3;

import java.util.LinkedList;

/**
 * @Author: heiden
 * @Date: 2021/11/30 17:30
 * @Project: qrcode-utils
 */

/**
 * 计算每个滑动窗口最大值
 * w 窗口大小
 */
public class MaxWindow {

    public static int[] getMaxWindow(int[] ary, int w){
        if (ary == null || w < 1 || ary.length < w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[ary.length - w + 1];
        int index = 0;
        for (int i = 0; i < ary.length; i++){
            while (!qmax.isEmpty() && ary[i] >= ary[qmax.peekLast()]){
                qmax.pollLast();
            }
            qmax.add(i);
            if ((i - w) == qmax.peekFirst()){
                qmax.pollFirst();
            }
            if (i >= w -1){
                res[index++] = ary[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] ary = new int[]{4,3,5,4,3,3,6,7};
        int[] wmax = getMaxWindow(ary, 3);
        for (int i = 0; i < wmax.length; i++){
            System.out.println("wmax=" + wmax[i]);
        }
    }
}
