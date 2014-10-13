

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 */
考虑到要求用O(nlogn)的时间复杂度和constant space complexity来sort list，自然而然想到了merge sort方法。同时我们还已经做过了merge k sorted list和merge 2 sorted list。这样这个问题就比较容易了。

不过这道题要找linkedlist中点，那当然就要用最经典的faster和slower方法，faster速度是slower的两倍，当faster到链尾时，slower就是中点，slower的next是下一半的开始点。

解决了这些问题，题目就很容易解出了。
public class SortList {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = slow.next;
		slow.next = null;
		slow = sortList(head);
		fast = sortList(fast);
		return merge(slow, fast);
	}

	private ListNode merge(ListNode slow, ListNode fast) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (slow != null && fast != null) {
			if (slow.val < fast.val) {
				cur.next = slow;
				slow = slow.next;
			} else {
				cur.next = fast;
				fast = fast.next;
			}
			cur = cur.next;
		}
		if (slow != null) {
			cur.next = slow;
		} else if (fast != null) {
			cur.next = fast;
		}
		return head.next;
	}
}



