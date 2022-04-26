package org.example.stack.s3;

/**
 * 计算最大子序列和
 */
public class MaxSubSeqSum {
    public static int calcMaxSubseqSum(int[] a){
       int curSum,maxSum;
       curSum = maxSum = 0;
       for(int i = 0; i < a.length; i++){
           curSum += a[i];
           if(curSum > maxSum){
               maxSum = curSum;
           }else if(curSum < 0){
               curSum = 0;
           }
       }
       return maxSum;
    }

    public static void main(String[] args){
        int[] a = {-2,11,-4,13,-5,-2};
        int maxSum = calcMaxSubseqSum(a);
        System.out.println("max seq sum is " + maxSum);
    }
    
}
