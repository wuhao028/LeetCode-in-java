

/** 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *只考虑了相近两个数相同的情况，因为已经排好序列，所以相近
 */

public class RemoveDuplicatesfromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		int cur = head.val;
		ListNode p = head.next;
		ListNode prev = head;
		while (p != null) {
			if (p.val == cur) {
				prev.next = p.next;
				p = prev.next;
			} else {
				cur = p.val;
				prev = prev.next;
				p = p.next;
			}
		}
		return head;
	}
}










































