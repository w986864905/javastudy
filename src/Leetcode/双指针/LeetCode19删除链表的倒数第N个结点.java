package Leetcode.双指针;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class LeetCode19删除链表的倒数第N个结点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        for(int i = 0 ;i < n && pre != null;i++){
            pre = pre.next;
        }
        if(pre == null){
            return head.next;
        }
        ListNode move = head;
        while(pre.next != null){
            pre = pre.next;
            move = move.next;
        }
        move.next = move.next.next;
        return head;
    }
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}



