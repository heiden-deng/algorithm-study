package org.example.stack.s3;

import java.util.Stack;

/**
 * @Author: heiden
 * @Date: 2021/12/2 17:04
 * @Project: qrcode-utils
 */

/**
 * 计算有0，1值组成的矩阵中，由1组成面积最大的长方形面积
 * 基本思路，将矩阵按行分割，从最上面一行开始计算height表示该行的高度，每一行的高度由上一行高度加上本行的元素值,如果本行该位置值为0，则行高为0
 * 对于矩阵
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 用户height表示行高
 * 对于第一行：height = 1,0,1,1
 * 对于第二行 height = 2,1,2,2,
 * 对于第三行 height = 3,2,3,0  （非0值为所在列值之和）
 * 分别计算每一行的height所表示的直方图的其中矩形面积最大值，所有行计算的取得的最大值即为最终结果
 */
public class CalcMaxRec {

    public int calMaxRec(int[][] map){
        if (map == null || map.length == 0 || map[0].length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                height[j] = map[i][j] == 0 ? 0 : (height[j] + 1);
            }
            maxArea = Math.max(calMaxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public int calMaxRecFromBottom(int[] height){
        if (height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++){
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]){
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int areaValue = (i - k - 1)*height[j];
                maxArea = Math.max(areaValue, maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int areaValue = (height.length - k - 1)*height[j];
            maxArea = Math.max(areaValue, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args){
        CalcMaxRec calcMaxRec = new CalcMaxRec();
        int[][] map = new int[][]{{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        int maxArea = calcMaxRec.calMaxRec(map);
        System.out.println("result = " + maxArea);
    }
}
