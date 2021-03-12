package Leetcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public static String printListNode(ListNode head){
        List<String> sb = new ArrayList<>();
        while (head!=null){
            sb.add(head.val+"");
            head = head.next;
        }
        return sb.stream().collect(Collectors.joining(",","[","]"));
    }
}
