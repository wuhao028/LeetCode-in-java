

/** 
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists. 
 */
 
 /** 
 * Definition for singly-linked list. 
 * public class ListNode { 
 *     int val; 
 *     ListNode next; 
 *     ListNode(int x) { val = x; next = null; } 
 * } 
 */ 
 

public class MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		if (l1 != null) {
			cur.next = l1;
		} else {
			cur.next = l2;
		}
		return head.next;
	}
}


//my version

public class merge{

   public merge(ListNode l1 ,ListNode l2){
   ListNode l3;
   while(l1!=null&&l2!=null){
    if(l1.val<l2.val){
	l3.next=l1
	l1=l1.next;
	}
   else if (l1.val>l2.val){
   l3.next=l2;
   l2=l2.next;
   }
   if(l1!=null){
      l3.next=l1;
   }else {
      l3.next=l2;
   }
   }
   return 
   
   }
}
























