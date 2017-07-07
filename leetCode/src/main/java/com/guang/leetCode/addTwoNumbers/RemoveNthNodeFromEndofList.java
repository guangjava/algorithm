package com.guang.leetCode.addTwoNumbers;

public class RemoveNthNodeFromEndofList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode p=head,q=head;
		for(int i=0; i<n; i++){
			p = p.next;
		}
		if (p == null) {
			return head.next;
		}
		while (p.next != null) {
			p = p.next;
			q = q.next;
		}
		q.next = q.next.next;
		return head;
	}
}
