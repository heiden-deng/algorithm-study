package org.example.stack.s3;

/**
 * @Author: heiden
 * @Date: 2021/12/17 15:23
 * @Project: qrcode-utils
 */

/**
 * 复制一个链表，链表节点有个rand字段，随机指向其他节点
 * 思路：
 * 1，先在每个节点后面新建一个节点，其value值和前一个节点值相同，
 * 2，新节点的rand值为前一个节点的rand值的后一个节点
 * 举例
 *
 * 1->3->2>4->null
 * 加上1的rand指向2节点，3节点的rand执行1节点，2指向null，
 * 新建节点后变成
 * 1->1`->3->3`->2->2`->4->4`->null
 * 1`的rand节点为1节点的rand节点指向的2节点的后一个节点即2`
 * NodeCopy.rand = preNode.rand.next
 */
public class CopyLinklistWithRandPtr {
    class RNode{
        public int value;
        public RNode next;
        public RNode rand;

        public RNode(int value){
            this.value = value;
        }
    }

    public RNode copyLinklist(RNode head){
        if (head == null){
            return null;
        }
        RNode cur = head;
        RNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = new RNode(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        RNode copyNode = null;
        while (cur != null){
            next = cur.next.next;
            copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        RNode res = head.next;
        cur = head;

        while (cur != null){
            next = cur.next.next;
            copyNode = cur.next;
            copyNode.next = next != null ? next.next : null;
            cur.next = next;
            cur = next;
        }
        return res;


    }
}
