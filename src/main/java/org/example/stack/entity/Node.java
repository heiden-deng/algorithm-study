package org.example.stack.entity;

/**
 * @Author: heiden
 * @Date: 2021/12/17 09:23
 * @Project: qrcode-utils
 */
public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
