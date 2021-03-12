package Leetcode.everyday.day20210107;

import Leetcode.common.ListNode;

import java.util.*;

class Solution {



    public static void main(String[] args) {
        //System.out.println(ListNode.printListNode(mergeTwoLists(new ListNode(1,new ListNode(3)),new ListNode(2,new ListNode(4)))));
        //System.out.println(new Solution().isValid("{}"));
        System.out.println(generateParenthesis(3));
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c:s.toCharArray()){
            if (c== '{'){
                stack.push('}');
            } else if (c== '['){
                stack.push(']');
            } else if (c== '('){
                stack.push(')');
            } else if (stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode head = result;
        while (l1 != null || l2 != null){
            if (l1 != null && l2 != null){
                if (l1.val <= l2.val){
                    result.next = l1;
                    l1 = l1.next;
                }else {
                    result.next = l2;
                    l2 = l2.next;
                }
            }else if (l1 != null){
                result.next = l1;
                l1 = l1.next;
            }else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }
        return head.next;
    }
    private static List<String> res = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return res;
    }
    private static void dfs(int left, int right, String curStr) {
        // 左右括号都不剩余了，递归终止
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 如果左括号还剩余的话，可以拼接左括号
        if (left > 0) {
            dfs(left - 1, right, curStr + "(");
        }
        // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
        if (right > left) {
            dfs(left, right - 1, curStr + ")");
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists,int l ,int r){
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }
    public static List<Integer> getAll(ListNode head){
        List<Integer> result = new ArrayList<>();
        while (null != head){
            result.add(head.val);
            head = head.next;
        }
        return result;
    }

}