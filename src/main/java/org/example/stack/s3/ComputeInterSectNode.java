package org.example.stack.s3;

/**
 * @Author: heiden
 * @Date: 2021/12/20 15:53
 * @Project: qrcode-utils
 */

import org.example.stack.entity.Node;

/**
 * 计算两个链表第一个相交的节点，
 * 思路：
 * 1、判断两个链表是否是包含环的链表；
 * 2，如果是都是无环链表，则计算方法：
 *    a,计算链表长度，并保存最后一个链表节点，如果最后一个链表节点不相同，则链表不相交，
 *    b,如果相交，则最长的链表从头先走abs(len1-len2)步，然后两个链表同时往尾部走，第一个地址相同的节点即为第一个相交的
 *    节点。
 * 3，如果一个是无环链表，另外一个是有环链表，则肯定不相交
 * 4，如果都是有环节点，则分情况：
 *   分别计算两个链表入环节点，loop1和loop2,
 *   相交点在环外，和相交点在环上。
 *   a,相交点在环外，loop1==loop2，则安装2.b步骤计算，两个子链表的尾节点设置为loop1和loop2，计算第一个相交节点
 *   b,相交点在换上，从loop1开始变量，如果能找到loop2，则两个链表相交，此时返回loop1或者loop2作为第一个相交的节点均可，
 *   如果找不到，则两个链表不相交
 */
public class ComputeInterSectNode {

    /**
     * 判断一个链表是否是有环链表
     * @param head
     * @return
     */
    public Node getLoopNode(Node head){

        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        if (head.next == head){
            return head;
        }

        Node oneStep = head.next; //每次走一步
        Node twoStep = head.next.next;//每次走两步
        while (oneStep != twoStep){
            if (twoStep.next == null || twoStep.next.next == null){
                return null;
            }
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }
        twoStep = head;
        while (twoStep != oneStep){
            twoStep = twoStep.next;
            oneStep = oneStep.next;
        }
        return oneStep;
    }

    /**
     * 计算两个无环链表的第一个相交节点，
     * @param h1  第一个链表头结点
     * @param h2
     * @param e1  第一个链表最后一个节点
     * @param e2  第二个链表最后一个节点
     * @return
     */
    public Node getNoloopNode(Node h1, Node h2, Node e1, Node e2){
        int l1 = 0, l2 = 0;
        Node cur1 = h1;
        Node cur2 = h2;
        if (h1 == null || h2 == null){
            return null;
        }
        while (cur1 != null){
            l1++;
            if (e1 == null && cur1.next == null){
                e1 = cur1;
            }
            cur1 = cur1.next;
        }
        while (cur2 != null){
            l2++;
            if (e2 == null && cur2.next == null){
                e2 = cur1;
            }
            cur2 = cur2.next;
        }
        if (e1 != e2){
            return null;
        }
        cur1 = h1;
        cur2 = h2;
        while(l1 > l2){
            cur1 = cur1.next;
            l1--;
        }
        while(l1 < l2){
            cur2 = cur2.next;
            l2--;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 判断两个有环链表是否相交，如果相交则返回第一个相交的节点
     * @param h1
     * @param h2
     * @param loop1
     * @param loop2
     * @return
     */
    public Node bothLoop(Node h1, Node h2, Node loop1, Node loop2){
        if (loop1 == loop2){//相交点在环外
            return getNoloopNode(h1, h2, loop1, loop2);
        }else{
            Node cur1 = loop1.next;
            while(cur1 != loop1 && cur1 != loop2){//从loop1开始在环中查找loop2节点，
                cur1 = cur1.next;
            }
            if (cur1 == loop1){
                return null;
            }
            return loop1;
        }
    }

    /**
     * 算法入口
     * @param h1
     * @param h2
     * @return
     */
    public Node getInsectNode(Node h1, Node h2){
        if (h1 == null || h2 == null){
            return null;
        }
        Node loop1 = null;
        Node loop2 = null;

        loop1 = getLoopNode(h1);
        loop2 = getLoopNode(h2);

        if (loop1 == null && loop2 == null){
            return getNoloopNode(h1, h2, null, null);
        }else if (loop1 != null && loop2 != null){
            return bothLoop(h1, h2, loop1, loop2);
        }else{
            return null;
        }


    }
}
