package Leetcode.day20200707;

import Leetcode.common.ListNode;
import Leetcode.common.MyLinkedList;

/**
 * 两数相加
 */
public class addTwo {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next=new ListNode(4);
        l1.next.next=new ListNode(3);;

        ListNode l2 = new ListNode(5);
        l2.next=new ListNode(6);
        l2.next.next=new ListNode(4);

        ListNode res = addTwoNumbers(l1,l2);
        MyLinkedList L1 = new MyLinkedList(res);
        L1.printLink();
    }

    /**
     * 思路1.直接相加判断是否大于10，思路2.转换为字符串再反转字符串利用封装转为整形相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        /**ListNode res = new ListNode(0);
        ListNode  p = l1;ListNode q = l2;ListNode curr = res;//p存放l1，q存放l2，curr存放res
        int k = 0;int x = 0;int y = 0;//k存放是否进位，x存放l1的值，y存放l2的值
        while (p!=null || q!=null){
            x=(p!=null) ? p.val : 0;
            y=(q!=null) ? q.val : 0;
            if (x+y+k < 10){
                curr.next = new ListNode(x+y+k);
                k = 0;
            }else {
                curr.next = new ListNode(x+y+k-10);
                k = 1;
            }
            if (p!=null){
                p = p.next;
            }
            if (q!=null){
                q = q.next;
            }
            curr = curr.next;
        }
        if (k == 1){
            curr.next = new ListNode(1);
        }
        return res.next;*/
        ListNode res = new ListNode(0);
        ListNode p = l1, q = l2, curr = res;
        int k = 0;int x = 0;int y = 0;
        while (p!=null || q!=null) {
            x = (p != null) ? p.val : 0;
            y = (q != null) ? q.val : 0;
            if ((x + y + k) < 10) {
                curr.next = new ListNode(x + y + k);
                k = 0;
            }else{
                curr.next = new ListNode(x + y + k - 10);
                k = 1;
            }
            if(p!=null) p = p.next;
            if(q!=null) q = q.next;
            curr = curr.next;
        }
        if(k == 1){
            curr.next = new ListNode(1);
        }
        return res.next;
    }
}
