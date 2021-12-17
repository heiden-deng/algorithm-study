package org.example.tools;

import org.example.stack.entity.Node;

/**
 * @Author: heiden
 * @Date: 2021/12/17 09:24
 * @Project: qrcode-utils
 */
public class NodeUtils {
    public Node createLinklist(int[] data){
        Node head = null;
        Node cur = null;
        for (int i = 0; i < data.length; i++){
            Node node = new Node(data[i]);
            if (i == 0){
                head = node;
                cur = node;
                continue;
            }
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public void printLinklist(Node head){
        Node cur = head;
        System.out.println("print linklist ");
        while (cur != null){
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
