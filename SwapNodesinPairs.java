

/** 
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */

public class SwapNodesinPairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return null;
		else if (head.next == null) {
			return head;
		} else {
			ListNode nextList = swapPairs(head.next.next);
			ListNode tmp = head.next;
			head.next = nextList;
			tmp.next = head;
			return tmp;
		}
	}
}

public ListNode swapPairs(ListNode head) {
      if(head == null || head.next == null)
        return head;
    
      ListNode fakehead = new ListNode(-1);
      fakehead.next = head;
      
      ListNode ptr1 = fakehead;
      ListNode ptr2 = head;
      
      while(ptr2!=null && ptr2.next!=null){
          ListNode nextstart = ptr2.next.next;
          ptr2.next.next = ptr2;
          ptr1.next = ptr2.next;
          ptr2.next = nextstart;
          ptr1 = ptr2;
          ptr2 = ptr2.next;
      }
    return fakehead.next;
  }