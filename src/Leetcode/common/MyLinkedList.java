package Leetcode.common;

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
     * 构造函数
     * @param head
     */
    public MyLinkedList(ListNode head) {
        this.head = head;
    }
}
