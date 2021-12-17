package org.example.stack.s3;

/**
 * @Author: heiden
 * @Date: 2021/12/1 16:01
 * @Project: qrcode-utils
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 根据数组生成二叉树，要求，父节点值比子节点值大
 */

class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }
}
public class MaxTree {


    public void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map){
        Node popNode = stack.pop();
        if (stack.isEmpty()){
            map.put(popNode, null);
        }else{
            map.put(popNode, stack.peek());
        }
    }

    public Node createMaxTree(int[] ary){
        Node[] nodeAry = new Node[ary.length];
        for (int i = 0; i < ary.length; i++){
            nodeAry[i] = new Node(ary[i]);

        }
        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMax= new HashMap<Node, Node>();
        HashMap<Node, Node> rBigMax= new HashMap<Node, Node>();

        for (int i = 0; i < nodeAry.length; i++){
            Node curNode = nodeAry[i];
            while (!stack.isEmpty() && curNode.value > stack.peek().value){
                popStackSetMap(stack, lBigMax);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, lBigMax);
        }

        for (int i = nodeAry.length - 1; i >= 0; i--){
            Node curNode = nodeAry[i];
            while (!stack.isEmpty() && curNode.value > stack.peek().value){
                popStackSetMap(stack, rBigMax);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, rBigMax);
        }
        Node header = null;
        for (int i = 0; i < nodeAry.length; i++){
            Node curNode = nodeAry[i];
            Node left = lBigMax.get(curNode);
            Node right = rBigMax.get(curNode);

            if (left == null && right == null){
                header = curNode;
            }else if (left == null){
                if (right.left == null){
                    right.left = curNode;
                }else{
                    right.right = curNode;
                }
            }else if (right == null){
                if (left.left == null){
                    left.left = curNode;
                }else{
                    left.right = curNode;
                }
            }else{
                Node parentNode = left.value > right.value ? right:left;
                if (parentNode.left == null){
                    parentNode.left = curNode;
                }else{
                    parentNode.right = curNode;
                }
            }
        }
        return header;
    }
    public static void main(String[] args){
        MaxTree maxTree = new MaxTree();
        Node header = maxTree.createMaxTree(new int[]{3,4,5,1,2});
        System.out.println("hello");
    }
}
