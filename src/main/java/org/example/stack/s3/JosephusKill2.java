package org.example.stack.s3;

import org.example.stack.entity.Node;

/**
 * @Author: heiden
 * @Date: 2021/12/16 17:08
 * @Project: qrcode-utils
 */

/**
 * 在一个循环链表中，从某节点开始，依次喊数，从1喊到m,喊到m的节点删除，被删除节点的下一个节点从1又开始，喊到m的节点删除，继续，
 * 直到剩余一个节点，要求时间复杂度为O(N)
 * 解法：
 * 假设链表长度为i
 * 喊到A 的节点编号为B
 * A   B
 * 1   1
 * 2   2
 * i   i
 * i+1  1
 * i+2  2
 * 总结规律:喊到A的节点编号为B
 * (公式1)B = (A - 1)%i + 1
 *
 * num(i)表示大小为i的循环链表喊到m之后，最终剩下节点编号值,现在需要计算num(i)的值
 * 已知num(1)=1
 * 如果能够推算出num(t)和num(t-1）的关系，就能递归计算出num(i)的值
 *
 * 对于大小为i的循环链表，假设该轮是s节点喊到m,即有如下
 *   i长度链表编号   i-1长度链表编号
 *
 *   s-2            i-2
 *   s-1            i-1
 *   s              本节点删除
 *   s+1             1
 *   s+2             2
 *   总结规律,假设i长度链表编号为old, 删除s节点后长度为i-1的链表编号为new，则有
 *   （公式2）old = (new + s -1)%i + 1
 *
 *   由公式1知喊m的节点编号为s=(m-1)%i+1，将该值带入到公式2,可以活动old和new的关系
 *   old = (new + (m-1)%i+1 - 1)%i + 1 = (new + m -1)%i + 1
 *   具体实现参考下面的getLive函数
 *
 *
 */


public class JosephusKill2 {

    public Node josephuskill2(Node head ,int m){
        if (head == null || head.next == head || m < 1){
            return head;
        }
        Node cur = head.next;
        int len = 1;
        while (cur != head){//计算链表长度
            len++;
            cur = cur.next;
        }

        int pos = getLive(len, m);  //去除最终剩下的节点编号
        while(--pos != 0){
            head = head.next;
        }
        head.next = head;
        return head;
    }


    /**
     * 对应上述的num函数，求大小为i的循环链表，依次删除序号为m节点，最终剩下的节点编号
     * @param i 循环链表的节点数
     * @param m 挑选值m
     * @return
     */
    public int getLive(int i ,int m){
        if (i == 1){
            return 1;
        }else{
            return (getLive(i-1,m) + m - 1)%i + 1;
        }
    }
}
