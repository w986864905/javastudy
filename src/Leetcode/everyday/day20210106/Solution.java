package Leetcode.everyday.day20210106;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        System.out.println(printListNode(new Solution().removeNthFromEnd1(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))),1)));
        //System.out.println(printListNode(new Solution().removeNthFromEnd1(new ListNode(1),1)));
    }

    public static String printListNode(ListNode head){
        StringBuilder sbb = new StringBuilder();
        ListNode head1 = head;
        sbb.append("[");
        while (head!=null){
            sbb.append(head.val);
            sbb.append(",");
            head = head.next;
        }
        if (head1 != null){
            sbb.deleteCharAt(sbb.lastIndexOf(","));
        }
        sbb.append("]");
        return sbb.toString();
    }
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        return removeNode(head,n)==n?head.next:head;
    }
    public int removeNode(ListNode node,int n) {
        if(node.next == null) {
            return 1;
        }
        int m = removeNode(node.next, n);
        if(m == n) {
            node.next = node.next.next;
        }
        return m+1;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n==1 && head.next == null){
            return null;
        }
        if (n==1 && head.next.next == null){
            head.next = null;
            return head;
        }
        ListNode index = head;
        ListNode pre = head;
        int m = 0;
        //保留当前节点第n个节点
        while (null != index.next){
            //先让index走n个节点
            if (m < n){
                index = index.next;
                m++;
            }else {
                index = index.next;
                pre = pre.next;
            }
        }
        //如果m == n-1 表明其正好为n长度将head丢弃
        if (m == n-1){
            return head.next;
        }else if (m == n){
            //m = n 说明链表长度大于n
            if (pre.next != null){
                //删除pre下一个节点
                pre.next = pre.next.next;
            }
        }
        return head;
    }






}
