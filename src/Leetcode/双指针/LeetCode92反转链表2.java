package Leetcode.双指针;

import Leetcode.common.ListNode;

/**
 * @ClassName LeetCode92反转链表2
 * @Description
 * @Author 王品峰
 * @Date 2021/9/23 14:16
 * @Version 1.0
 */
public class LeetCode92反转链表2 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode headPre = new ListNode(0);
        headPre.next = head;
        ListNode pre = headPre;
        for(int i = 1; i < left; i++){
            pre = pre.next;
        }
        head = pre.next;
        for(int i = left; i < right; i++){
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return headPre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null)))));
        ListNode node =  reverseBetween(head,2,4);
        System.out.println(ListNode.printListNode(node));
    }

}
