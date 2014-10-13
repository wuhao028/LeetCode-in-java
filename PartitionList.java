/** 
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
 
 
  /** 
 * Definition for singly-linked list. 
 * public class ListNode { 
 *     int val; 
 *     ListNode next; 
 *     ListNode(int x) { val = x; next = null; } 
 * } 
 */ 
 
/*时间复杂度为O(n),空间复杂度也为O（n）*/
 
 
public class PartitionList {
	public ListNode partition(ListNode head, int x) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode slow = start;
        while (slow.next != null) {
            if (slow.next.val < x) {
                slow = slow.next;
            } else {
                break;
            }
        }
        ListNode fast = slow;
        while (fast.next != null) {
            if (fast.next.val >= x) {
                fast = fast.next;
            } else {
                ListNode target = fast.next;
                fast.next = target.next;
                target.next = slow.next;
                slow.next = target;
                slow = slow.next;
            }
        }
        return start.next;
    }
}


//遍历ListNode,分别把大于x和小于x的数分组，最后组合

package partition_list;

import common.ListNode;

public class PartitionList {

    public class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode lessHead = null;
            ListNode lessTail = null;
            ListNode greaterHead = null;
            ListNode greaterTail = null;
            ListNode p = head;
            while (p != null) {
                if (p.val < x) {
                    if (lessHead == null) {
                        lessHead = p;
                    } else {
                        lessTail.next = p;
                    }
                    lessTail = p;
                } else {
                    if (greaterHead == null) {
                        greaterHead = p;
                    } else {
                        greaterTail.next = p;
                    }
                    greaterTail = p;
                }
                p = p.next;
            }
            if (lessHead == null) {
                if (greaterTail != null) {
                    greaterTail.next = null;
                }
                return greaterHead;
            } else {
                lessTail.next = greaterHead;
                if (greaterTail != null) {
                    greaterTail.next = null;
                }
                return lessHead;
            }
        }
    }

    public static class UnitTest {

    }
}






























