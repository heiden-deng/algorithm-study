package org.example.stack.s3;

import org.example.stack.entity.TreeNode;


/**
 * 以o(1)空间消耗，o(n)的效率遍历二叉树
 * morris算法
 * 思路：
 * 1）当前节点为cur，将cur的左子树的最右节点的right指向cur,继续按步骤1处理cur的左子树，直到没有左子树，假定没有左子树的节点为node
 * 2) 从node开始移动到right节点指向的节点，假设移动到的节点为cur,并打印node节点值，并判断
 *    cur的左子树的最右节点的right是否指向cur
 *    a:如果是，则将cur的左子树的最右节点的right置为空，并打印cur节点，并将cur移动到cur.right节点，继续步骤2
 *    b: 如果不是，则继续步骤1处理
 */
public class MorrisVisiror {
  
    /**
     * 中序遍历二叉树
     * @param head
     */
    public static void visitTree(TreeNode head){
        if(head == null){
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                }
            }
            System.out.println("cur value:" + cur1.value);
            cur1 = cur1.right;
        }
    }

    /**
    * 先序遍历二叉树
     */
    public static void preVisitorTree(TreeNode head){
       if(head == null){
           return;
       }
       TreeNode cur1 = head;
       TreeNode cur2 = null;
       while(cur1 != null){
           cur2 = cur1.left;
           if(cur2 != null){
               while(cur2.right != null && cur2.right != cur1){
                   cur2 = cur2.right;
               }
               if(cur2.right == null){
                   cur2.right = cur1;
                   System.out.println("node value"+cur1.value);
                   cur1 = cur1.left;
                   continue;
               }else{
                   cur2.right = null;
               }
           }else{
               System.out.println("node value:" + cur2.value);
           }
           cur1 = cur1.right;
       }
    }


    public static void main(String[] arg){

    }
}
