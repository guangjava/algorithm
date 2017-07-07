package com.guang.leetCode.addTwoNumbers;
/*You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
利用链表处理两个数的加法
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8*/
public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		ListNode t = result;
		while (l1!=null || l2!=null) {
			int val = t.val;
			if (l1 != null) {
				val += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				val += l2.val;
				l2 = l2.next;
			}
			if (val >= 10) {
				val -= 10;
				t.val = val;
				//错误点，记得进位
				t.next = new ListNode(1);
				t = t.next;
			}
			else {
				t.val = val;
				//错误点，最高位不要出现0
				if (l1==null && l2==null) {
					break;
				}
				t.next = new ListNode(0);
				t = t.next;
			}
		}
		return result;
	}
}
