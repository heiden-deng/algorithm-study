package org.example.stack.s3;

import java.util.Stack;

/**
 * @Author: heiden
 * @Date: 2021/11/23 15:57
 * @Project: qrcode-utils
 */




public class ReverseStack1 {

    public static int getAndLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }
        else{
            int last = getAndLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = getAndLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void printStack(Stack<Integer> stack){
        Stack<Integer> outputStack = (Stack<Integer>) stack.clone();
        while (!outputStack.isEmpty()){
            System.out.println(outputStack.pop());
        }
    }
    public static void main(String[] args){
        Stack<Integer> st = new Stack<>();
        st.push(5);
        st.push(4);
        st.push(3);
        st.push(2);
        st.push(1);

        printStack(st);

        reverse(st);

        printStack(st);
    }
}
