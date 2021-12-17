package org.example.tools;

import java.util.Stack;

/**
 * @Author: heiden
 * @Date: 2021/11/23 16:54
 * @Project: qrcode-utils
 */
public class StackUtils {
    public static void printStack(Stack<Integer> stack){
        System.out.println("Stack content:");
        Stack<Integer> outputStack = (Stack<Integer>) stack.clone();
        while (!outputStack.isEmpty()){
            System.out.println(outputStack.pop());
        }
    }
}
