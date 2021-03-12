package Leetcode.everyday.day20210108;

import Leetcode.common.ListNode;

class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6))))));
        //swap(head,head.next,head.next.next.next);
        System.out.println(ListNode.printListNode(reverseKGroup(head,2)));
    }
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode index = new ListNode();
        index.next = head;
        ListNode resultNode = index;
        int i = 0;
        while (index.next != null){
            if (i%2 == 0 && index.next.next != null){
                swap(index,index.next,index.next.next.next);
            }
            i++;
            index = index.next;
        }
        return resultNode.next;
    }
    public static void swap(ListNode pre,ListNode head,ListNode next){
        pre.next = head.next;
        head.next.next = head;
        head.next = next;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode index = new ListNode(0,head);
        ListNode result = index;
        ListNode pre = head;
        ListNode next;
        int i = 1;
        while (head != null){
            if (i == 1){
                pre = head;
            }
            if (i == k){
                next = head.next;
                //截断
                head.next = null;
                index.next = reverse(pre);
                pre.next = next;
                index = pre;
                i = 1;
                head = next;
            }else {
                head = head.next;
                i++;
            }
        }
        return result.next;
    }
    public static ListNode reverse(ListNode head){
        ListNode pre = head;
        ListNode next = head.next;
        while (next != null){
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }
        head.next = null;
        return pre;
    }
}