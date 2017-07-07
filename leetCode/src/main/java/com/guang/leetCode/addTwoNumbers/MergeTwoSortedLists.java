package com.guang.leetCode.addTwoNumbers;

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode result,p,q,r;
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		p = l1;
		q = l2;
		if (l1.val < l2.val) {
			result = l1;
			p = p.next;
		}
		else {
			result = l2;
			q = q.next;
		}
		r = result;
		while (p!=null && q!=null) {
			if (p.val < q.val) {
				r.next = p;
				r = r.next;
				p = p.next;
			}
			else {
				r.next = q;
				r = r.next;
				q = q.next;
			}
		}
		if (p == null) {
			r.next = q;
		}
		else {
			r.next = p;
		}
		return result;
	}
}
