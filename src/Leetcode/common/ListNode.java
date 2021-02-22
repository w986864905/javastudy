package Leetcode.common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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
}
