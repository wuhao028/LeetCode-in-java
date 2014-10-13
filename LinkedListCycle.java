

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 */

public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		if (head == null) return false;
		ListNode fast = head;
		ListNode late = head;
		do {
			fast = fast.next;
			late = late.next;
			if (late == null) {
				return false;
			} else {
				late = late.next;
				if (fast == late) {
					return true;
				}
			}
		} while(fast != null && late != null);
		return false;
	}
}





public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        
        ListNode Faster = head, Slower = head;
        
        while(Faster.next!=null && Faster.next.next!=null){
            Slower = Slower.next;
            Faster = Faster.next.next;
            
            if(Faster == Slower)
                return true;
        }
        return false;
    }