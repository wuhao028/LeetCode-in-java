/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 */

 /** 
 * Definition for singly-linked list. 
 * public class ListNode { 
 *     int val; 
 *     ListNode next; 
 *     ListNode(int x) { val = x; next = null; } 
 * } 
 */ 
/** 
 * Definition for binary tree 
 * public class TreeNode {  
 *     int val; 
 *     TreeNode left; 
 *     TreeNode right; 
 *     TreeNode(int x) { val = x; } 
 * } 
 */ 
 
up-down 策略
 
public class ConvertSortedListtoBinarySearchTree {
	public TreeNode sortedListToBST(ListNode head) {
		return sortedListToBST(head, null);
	}

	private TreeNode sortedListToBST(ListNode start, ListNode end) {
		if (start == end) {
			return null;
		} else if (start.next == end) {
			return new TreeNode(start.val);
		} else {
			ListNode fast = start, slow = start;
			
			//fast是偶数节点，slow是奇数节点，while循环完成就找到中间节点
			while (fast.next != end && fast.next.next != end) {
				fast = fast.next.next;
				slow = slow.next;
			}
			TreeNode left = sortedListToBST(start, slow);
			TreeNode right = sortedListToBST(slow.next, end);
			TreeNode root = new TreeNode(slow.val);
			root.left = left;
			root.right = right;
			return root;
		}
	}
}

package convert_sorted_list_to_binary_search_tree;

import common.ListNode;
import common.TreeNode;

public class ConvertSortedListtoBinarySearchTree {

    public class Solution {
        private TreeNode sortedListToBST(ListNode head, ListNode[] tail,
                int begin, int end) {
            if (begin > end) {
                tail[0] = head;
                return null;
            }
            TreeNode p = new TreeNode(0);
            int mid = begin + (end - begin) / 2;
            p.left = sortedListToBST(head, tail, begin, mid - 1);
            p.val = tail[0].val;
            p.right = sortedListToBST(tail[0].next, tail, mid + 1, end);
            return p;
        }

        public TreeNode sortedListToBST(ListNode head) {
            ListNode p = head;
            int len = 0;
            while (p != null) {
                len++;
                p = p.next;
            }
            return sortedListToBST(head, new ListNode[1], 0, len - 1);
        }
    }

    public static class UnitTest {

    }
}

































