

import java.util.ArrayList;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Note:
 * 
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

public class RecoverBinarySearchTree {
	public void recoverTree(TreeNode root) {
		if (root == null)
			return;
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		findWrongNode(root, nodes);
		TreeNode a = null, b = null;
		int i = 0;
		for (i = 0; i < nodes.size() - 1; i++) {
			if (nodes.get(i).val > nodes.get(i + 1).val) {
				a = nodes.get(i);
				break;
			}
		}
		for (int j = i + 1; j < nodes.size() - 1; j++) {
			if (nodes.get(j).val > nodes.get(j + 1).val) {
				b = nodes.get(j + 1);
				break;
			}
		}
		if (b == null)
			b = nodes.get(i + 1);

		int t = a.val;
		a.val = b.val;
		b.val = t;
	}

	private void findWrongNode(TreeNode root, ArrayList<TreeNode> nodes) {
		if (root == null)
			return;
		findWrongNode(root.left, nodes);
		nodes.add(root);
		findWrongNode(root.right, nodes);
	}
}



题解：

 解决方法是利用中序遍历找顺序不对的两个点，最后swap一下就好。

 因为这中间的错误是两个点进行了交换，所以就是大的跑前面来了，小的跑后面去了。

 所以在中序便利时，遇见的第一个顺序为抵减的两个node，大的那个肯定就是要被recovery的其中之一，要记录。

 另外一个，要遍历完整棵树，记录最后一个逆序的node。

 简单而言，第一个逆序点要记录，最后一个逆序点要记录，最后swap一下。

 因为Inorder用了递归来解决，所以为了能存储这两个逆序点，这里用了全局变量，用其他引用型遍历解决也可以。

 

代码如下：

 

public class Solution {
    TreeNode pre;
    TreeNode first;
    TreeNode second;
      
    public void inorder(TreeNode root){  
        if(root == null)  
            return;  

        inorder(root.left);  
        if(pre == null){  
            pre = root;  //pre指针初始
        }else{  
            if(pre.val > root.val){  
                if(first == null){  
                    first = pre;//第一个逆序点
                }  
                second = root;  //不断寻找最后一个逆序点
            }  
            pre = root;  //pre指针每次后移一位
        }  
        inorder(root.right);  
    }  
      
    public void recoverTree(TreeNode root) {  
        pre = null;  
        first = null;  
        second = null;  
        inorder(root);  
        if(first!=null && second!=null){   
            int tmp = first.val;  
            first.val = second.val;  
            second.val = tmp;  
        }  
    }