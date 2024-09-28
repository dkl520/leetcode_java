package com.leetcode2.org.链表;


import java.util.ArrayList;
import java.util.Objects;

class Solution234 {

    public boolean isPalindrome(ListNode head) {
        // 创建一个 ArrayList 来保存链表的值
        ArrayList<Integer> linkedList = new ArrayList<Integer>();

        // 将链表的头节点的值添加到 ArrayList 中
        linkedList.add(head.val);

        // 创建一个临时节点，从头节点的下一个节点开始遍历
        ListNode temp = head.next;

        // 遍历链表，将每个节点的值添加到 ArrayList 中
        while (temp != null) {
            linkedList.add(temp.val);
            temp = temp.next;
        }

        // 定义两个指针，分别指向 ArrayList 的开头和结尾
        int left = 0;
        int right = linkedList.size() - 1;



            // 使用双指针法检查 ArrayList 是否是回文
            while (left < right) {
                // 如果对应位置的值不相等，则链表不是回文，返回 false
                if (!Objects.equals(linkedList.get(left), linkedList.get(right))) {
                    return false;
                }
                // 移动左指针向右
                left++;
                // 移动右指针向左
                right--;
            }
        // 如果所有对应位置的值都相等，则链表是回文，返回 true
        return true;
    }
}
