package Leetcode.双指针;

import Leetcode.common.ListNode;

/**
 * @ClassName LeetCode142环形链表II
 * @Description
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * @Author 王品峰
 * @Date 2021/2/23 17:19
 * @Version 1.1.
 */
public class LeetCode142环形链表II {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head.next,fast = head.next.next;
        //判断是否有环
        while (fast != slow){
            if (fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //找到环路开端
        fast = head;
        while (fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode first = new ListNode(2);
        ListNode sc = new ListNode(0);
        ListNode th = new ListNode(-1);
        head.next = first;
        first.next = sc;
        sc.next = th;
        th.next = head;
        System.out.println(detectCycle(head).val);
    }
}
