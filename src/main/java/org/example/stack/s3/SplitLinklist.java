package org.example.stack.s3;

/**
 * @Author: heiden
 * @Date: 2021/12/17 10:04
 * @Project: qrcode-utils
 */

import org.example.stack.entity.Node;

/**
 * 将一个链表按照pivot值重新排列，使得链表前面部分小于pivot值，尾部部分大于pivot值，中间值等于pivot(如果存在)
 * 进阶要求，新链表中节点顺序和原链表保持一致
 */
public class SplitLinklist {

    public Node ListParitition(Node head, int pivot){
        Node sH = null; //小的头
        Node sT = null; //小的尾
        Node eH = null; //相等的头
        Node eT = null; //相等的尾
        Node bH = null; //大的头
        Node bT = null; //大的尾

        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;

            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else{
                    eT.next = head;
                    eT = head;
                }
            }else{
                if (bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        if (sT != null){
            sT.next = eH;
            eT = (eT == null)?sT:eT;
        }
        if (eT != null){
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }
}
