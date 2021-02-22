package Leetcode.common;

import java.util.Hashtable;
import java.util.List;

/**
 * 链表常见算法
 */
public class MyLinkedList {
    ListNode head = null;
    /**
     *链表添加节点
     */
    public void addNode(int val){
        ListNode newNode = new ListNode(val);
        if (head == null){
            head = newNode;
            return;
        }
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }
    /**
     * 链表删除节点
     */
    public boolean deleteNode(int index){
        if (index<1 || index>length()){//删除节点不存在
            return false;
        }
        if (index == 1){
            head = head.next;
            return true;
        }
        ListNode preNode = head;
        ListNode curNode = preNode.next;
        int i = 1;
        while (curNode != null){
            if (i == index){
                preNode.next = curNode.next;
                return true;
            }
            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }
        return true;
    }
    /**
     * 求链表的长度
     */
    public int length(){
        int length = 0;
        ListNode curNode = head;
        while (curNode!=null){
            length++;
            curNode = curNode.next;
        }
        return length;
    }
    /**
     * 打印链表
     */
    public void printLink(){
        ListNode curNode = head;
        while (curNode != null){
            System.out.print(curNode.val+" ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    /**
     * 选择排序算法
     * @return
     */
    public ListNode linkSort(){
        ListNode curNode = head;
        while (curNode != null){
            ListNode nextNode = curNode.next;
            while (nextNode != null){
                if (curNode.val > nextNode.val){
                    int temp = curNode.val;
                    curNode.val = nextNode.val;
                    nextNode.val = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 去掉重复元素
     * 调用hashtable.containsKey()来判断重复结点
     */
    public void distinctLink(){
        ListNode temp = head;
        ListNode pre = null;
        Hashtable<Integer,Integer> hb = new Hashtable<Integer, Integer>();
        while (temp != null){
            if (hb.containsKey(temp.val)){
                pre.next = temp.next;
            }else {
                hb.put(temp.val,1);
                pre = temp;
            }
            temp = temp.next;
        }
    }

    /**
     * 返回倒数第k个节点
     * 第一个指针比第二个多走k-1，当第一个指针到尾部时，第二个指针正好为k
     * @param k
     * @return
     */
    public ListNode findReverNode(int k){
        if (k < 1 || k > length()){
            return null;
        }
        ListNode first = head;
        ListNode second = head;
        for (int i = 0;i < k - 1; i++){
            first = first.next;
        }
        while (first.next != null){
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 查找正数第k个元素
     * @param k
     * @return
     */
    public ListNode findNode(int k){
        if (k < 1 || k > length()){
            return null;
        }
        ListNode temp = head;//存放当前指针
        for (int i = 0; i < k-1; i++){
            temp = temp.next;
        }
        return temp;
    }

    public void reserveLink(){
        ListNode curNode = head;//头结点
        ListNode preNode = null;//前一个节点
        while (curNode != null){
            ListNode nextNode = curNode.next;//保留下一个节点
            curNode.next = preNode;//指针反转
            preNode = curNode;//前节点后移
            curNode = nextNode;//当前节点后移
        }
        head = preNode;
    }
    /**
     * 反向输出链表
     * 方法一、先反转链表，再找出链表。遍历两次
     * 方法二、把链表中的数字放入栈中再输出，需要维护额外的栈空间
     * 方法三、采用递归基于方法二的原理
     */
    public void reservePrt(ListNode listNode){
        if (listNode != null){
            reservePrt(listNode.next);
            System.out.println(listNode.val+" ");
        }
    }

    /**
     * 寻找单链表的中间结点
     * 方法一、求出链表的长度，再遍历1/2链表长度，寻找链表的中间结点
     * 方法二、用两个指针，都从头开始，快指针一次遍历2给个结点，慢指针一次遍历一个结点，当快指针到尾部时，慢指针位置即中间结点
     * @return
     */
    public ListNode findMiddleNode(){
        ListNode slowPoint = head;
        ListNode quickPoint = head;
        while (quickPoint.next != null && quickPoint.next.next != null){
            slowPoint = slowPoint.next;
            quickPoint = quickPoint.next.next;
        }
        return slowPoint;
    }

    /**
     * 判断链表是否有环：
     * 设置快指针和慢指针，慢指针每次走一步，快指针每次走两步，当快指针与慢指针相等时就说明有环。
     * @return
     */
    public boolean isRinged(){
        if (head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 得到链表的最后一个结点
     * @return
     */
    public ListNode getLastNode(){
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 在不知道头结点的情况下删除指定结点：
     * 删除结点的重点在于找出其前结点，使其前结点的指针指向其后结点
     * 1.如果待删除的结点是尾结点，由于单链表不知道其前结点，无法删除
     * 2.如果删除的结点不是尾结点，则将其该结点与下一结点交换
     */
    public boolean deleteSpecialNode(ListNode n){
        if (n.next == null){
            return false;
        }else {
            //交换结点和其后续结点中的数据
            int temp = n.val;
            n.val = n.next.val;
            n.next.val = temp;
            //删除后一结点
            n.next = n.next.next;
            return true;
        }
    }

    /**
     * 两个链表相交则尾结点一定相同，比较两个链表的尾结点是否相同即可
     * @param head1
     * @param head2
     * @return
     */
    public boolean isCross(ListNode head1,ListNode head2){
        if (head1 == null || head2 == null){
            return false;
        }
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1.next != null){
            temp1 = temp1.next;
        }
        while (temp2.next != null){
            temp2 = temp2.next;
        }
        if (temp1 == temp2){
            return true;
        }
        return false;
    }

    public ListNode findFirstCrossPoint(MyLinkedList list1,MyLinkedList list2){
        //先判断是否有相交
        if (!isCross(list1.head,list2.head)){
            return null;
        }else {
            int length1 = list1.length();//链表1的长度
            int length2 = list2.length();//链表2的长度
            ListNode temp1 = list1.head;//链表1的头结点
            ListNode temp2 = list2.head;//链表2的头结点
            int len = length1 - length2;//链表1和链表2的长度差
            if (len > 0){
                for (int i = 0;i < len; i++){
                    temp1 = temp1.next;
                }
            }else{
                for (int i = 0;i < len; i++){
                    temp2 = temp2.next;
                }
            }
            while (temp1 != temp2){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1;
        }
    }

    /**
     * 构造函数
     * @param head
     */
    public MyLinkedList(ListNode head) {
        this.head = head;
    }
}
