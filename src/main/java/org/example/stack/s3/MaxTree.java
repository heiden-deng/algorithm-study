package org.example.stack.s3;

/**
 * @Author: heiden
 * @Date: 2021/12/1 16:01
 * @Project: qrcode-utils
 */

import org.example.stack.entity.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 根据数组生成二叉树，要求，父节点值比子节点值大
 */

public class MaxTree {


    public void popStackSetMap(Stack<TreeNode> stack, HashMap<TreeNode, TreeNode> map){
        TreeNode popNode = stack.pop();
        if (stack.isEmpty()){
            map.put(popNode, null);
        }else{
            map.put(popNode, stack.peek());
        }
    }

    public TreeNode createMaxTree(int[] ary){
        TreeNode[] nodeAry = new TreeNode[ary.length];
        for (int i = 0; i < ary.length; i++){
            nodeAry[i] = new TreeNode(ary[i]);

        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> lBigMax= new HashMap<TreeNode, TreeNode>();
        HashMap<TreeNode, TreeNode> rBigMax= new HashMap<TreeNode, TreeNode>();

        for (int i = 0; i < nodeAry.length; i++){
            TreeNode curNode = nodeAry[i];
            while (!stack.isEmpty() && curNode.value > stack.peek().value){
                popStackSetMap(stack, lBigMax);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, lBigMax);
        }

        for (int i = nodeAry.length - 1; i >= 0; i--){
            TreeNode curNode = nodeAry[i];
            while (!stack.isEmpty() && curNode.value > stack.peek().value){
                popStackSetMap(stack, rBigMax);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, rBigMax);
        }
        TreeNode header = null;
        for (int i = 0; i < nodeAry.length; i++){
            TreeNode curNode = nodeAry[i];
            TreeNode left = lBigMax.get(curNode);
            TreeNode right = rBigMax.get(curNode);

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
                TreeNode parentNode = left.value > right.value ? right:left;
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
        TreeNode header = maxTree.createMaxTree(new int[]{3,4,5,1,2});
        System.out.println("hello");
    }
}
