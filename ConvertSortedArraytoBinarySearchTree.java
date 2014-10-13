

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

 
 
 
 //空间为O(1)时间为O(n),迭代，top--down策略 
 
 //  Definition for singly-linked list.
 
class ListNode {
	int val;
	ListNode next;
 
	ListNode(int x) {
		val = x;
		next = null;
	}
}
 
// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
 
	TreeNode(int x) {
		val = x;
	}
}

public class ConvertSortedArraytoBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] num) {
		return sortedArrayToBST(num, 0, num.length - 1);
	}

	public TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;
			TreeNode left = sortedArrayToBST(num, start, mid - 1);
			TreeNode right = sortedArrayToBST(num, mid + 1, end);
			TreeNode node = new TreeNode(num[mid]);
			node.left = left;
			node.right = right;
			return node;
		}
		return null;
	}
}



// my version

public class convert{
 
  public  TreeNode convert(int[] num){
     return converttoBST(num,0,num.length-1)
	 }
	 
	 
	 public converttoBST(int[] num,int start,int end){
	 if(start<end){
	  mid=(start+end)/2;
	  TreeNode left=converttoBST(num,0,mid-1);
	  TreeNode right=converttoBST(num,mid+1,end);
	  mid=new TreeNode(num[mid]);  //注意这里是num[mid],不是mid
	  mid.left=left;
	  mid,right=right;
	  return mid;
	 }
	 return null;
	 }
}



























