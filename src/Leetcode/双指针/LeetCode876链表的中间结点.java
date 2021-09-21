package Leetcode.双指针;

public class LeetCode876链表的中间结点 {
    public ListNode middleNode(ListNode head) {
        ListNode pre = head;
        ListNode mid = head;
        while(pre.next != null){
            pre = pre.next.next;
            mid = mid.next;
            if(pre == null){
                return mid;
            }
        }
        return mid;
    }
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
