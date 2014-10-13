

/** 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

public class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		return validate(root.left, Integer.MIN_VALUE, root.val)
				&& validate(root.right, root.val, Integer.MAX_VALUE);
	}

	private boolean validate(TreeNode root, int min, int max) {
		if (root == null)
			return true;
		if (root.val > min && root.val < max) {
			return validate(root.left, min, root.val)
					&& validate(root.right, root.val, max);
		} else {
			return false;
		}
	}
}

第一种方法是中序遍历法。

因为如果是BST的话，中序遍历数一定是单调递增的，如果违反了这个规律，就返回false。

代码如下：
public boolean isValidBST(TreeNode root) {  
    ArrayList<Integer> pre = new ArrayList<Integer>();  
    pre.add(null);  
    return helper(root, pre);  
}  
private boolean helper(TreeNode root, ArrayList<Integer> pre)  
{  
    if(root == null)  
        return true; 
    
    boolean left = helper(root.left,pre); 
    
    if(pre.get(pre.size()-1)!=null && root.val<=pre.get(pre.size()-1))  
        return false;  
    pre.add(root.val);  
    
    boolean right = helper(root.right,pre);
    return left && right;  
}


/**
 * Definition for binary tree
 * struct TreeNode {
 * int val;
 * TreeNode *left;
 * TreeNode *right;
 * TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isValidBST(TreeNode *root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        //注意题目要求是 less than和greater than;
        stack<TreeNode*> S;
        TreeNode *pre = NULL, *p = root;
        while(p || S.empty() == false)
        {
            while(p)
            {
                S.push(p);
                p = p->left;
            }
            if(S.empty() == false)
            {
                p = S.top();
                S.pop();
                if(pre && p->val <= pre->val)return false;
                pre = p;
                p = p->right;
            }
        }
        return true;
    }
};

第二种方法是直接按照定义递归求解。 

“根据题目中的定义来实现，其实就是对于每个结点保存左右界，
也就是保证结点满足它的左子树的每个结点比当前结点值小，右子树的每个结点比当前结 点值大。
对于根节点不用定位界，所以是无穷小到无穷大，接下来当我们往左边走时，上界就变成当前结点的值，
下界不变，而往右边走时，下界则变成当前结点 值，上界不变。如果在递归中遇到结点值超越了自己的上
下界，则返回false，否则返回左右子树的结果。” 

public boolean isValidBST(TreeNode root) {  
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }  
      
    public boolean isBST(TreeNode node, int low, int high){  
        if(node == null)  
            return true;  
            
        if(low < node.val && node.val < high)
            return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);  
        else  
            return false;  
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	