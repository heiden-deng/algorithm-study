package org.example.stack.s3;

import org.example.tools.StackUtils;

import java.util.Stack;

/**
 * @Author: heiden
 * @Date: 2021/11/23 16:52
 * @Project: qrcode-utils
 */
public class StackSort {

    public static void sortStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while(!stack.isEmpty()){
            int num = stack.pop();
            if (!help.isEmpty()){
                if(num > help.peek()){
                    help.push(num);
                }else{
                    int swapNum = 0;
                    int swapCnt = 0;
                    while(!help.isEmpty() && ((swapNum = help.pop()) > num)){
                        swapCnt++;
                        stack.push(swapNum);
                    }
                    help.push(num);
                    while (swapCnt != 0){
                        help.push(stack.pop());
                        swapCnt--;
                    }
                }
            }else{
                help.push(num);
            }
        }
        stack.addAll(help);
    }


    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stack.push(3);

        StackUtils.printStack(stack);
        sortStack(stack);

        StackUtils.printStack(stack);
    }
}
