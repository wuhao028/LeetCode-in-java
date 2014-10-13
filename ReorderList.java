

/**
 * Given a singly linked list L: L0->L1->...->Ln-1->Ln,
 * reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 */

public class ReorderList {
	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;
		ListNode fast = head;
		ListNode late = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			late = late.next;
		}
		ListNode ret = new ListNode(0);
		ListNode cur = ret;
		ListNode leftHalf = head;
		ListNode rightHalf;
		if (fast.next != null) {
			rightHalf = reverseList(late.next);
			late.next = null;
		} else {
			rightHalf = reverseList(late);
			ListNode tmp = head;
			while (tmp.next != late) {
				tmp = tmp.next;
			}
			tmp.next = null;
		}
		leftHalf = head;
		while (leftHalf != null && rightHalf != null) {
			cur.next = leftHalf;
			leftHalf = leftHalf.next;
			cur = cur.next;
			cur.next = rightHalf;
			rightHalf = rightHalf.next;
			cur = cur.next;
		}
		if (leftHalf != null) {
			cur.next = leftHalf;
		} else if (rightHalf != null) {
			cur.next = rightHalf;
		}
		head = ret.next;
	}

	private ListNode reverseList(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}



// my version   没有写reverse函数

    public void reorderList(ListNode head) {
        if(head==null || head.next==null)
		 return;
		
		
		// 此处应采用fast slow 快慢指针来做
        int n=1;
        ListNode node=head;
        while(head.next!=null)
          n++;
        
        while(n/2>0)
         node=node.next;
        ListNode newhead=node.next;
        reverse(newhead);
        node.next=null;
       
       while(head.next!=null && node.net!=null){
          ListNode temp1=head.next;
          ListNode temp2=newhead.next;
		  
          head.next=newhead;
          newhead.next=temp1;
		  
          head=temp1;
          newhead=temp2;
       }
    }

	
	
	题解：

题目要重新按照 L0→Ln→L1→Ln-1→L2→Ln-2→…来排列，看例子1->2->3->4会变成1->4->2->3，拆开来看，是{1，2}和{4，3}的组合，而{4，3}是{3，4}的逆序。这样问题的解法就出来了。

第一步，将链表分为两部分。

第二步，将第二部分链表逆序。

第三步，将链表重新组合。

 

代码如下：

 public void reorderList(ListNode head) {
        if(head==null||head.next==null)
            return;
        
        ListNode slow=head, fast=head;
        ListNode firsthalf = head;
        while(fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode secondhalf = slow.next;
        slow.next = null;
        secondhalf = reverseOrder(secondhalf);
 
        while (secondhalf != null) {
            ListNode temp1 = firsthalf.next;
            ListNode temp2 = secondhalf.next;
 
            firsthalf.next = secondhalf;
            secondhalf.next = temp1;        
 
            firsthalf = temp1;
            secondhalf = temp2;
        }
        
    }
    
    public static ListNode reverseOrder(ListNode head) {
 
        if (head == null || head.next == null)
            return head;
 
        ListNode pre = head;
        ListNode curr = head.next;
 
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
 
        // set head node's next
        head.next = null;
 
        return pre;
    }