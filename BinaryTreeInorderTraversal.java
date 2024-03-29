

import java.util.ArrayList;
import java.util.Stack;

/**
 * For example: Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 * 3
 * return [1,3,2].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
非递归算法
public class BinaryTreeInorderTraversal {
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		if (root == null)
			return inOrder;
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.add(root);
		TreeNode p = root.left;
		while (!s.empty()) {
			while (p != null) {
				s.add(p);
				p = p.left;
			}
			TreeNode n = s.pop();
			inOrder.add(n.val);
			p = n.right;
			if (p != null) {
				s.add(p);
				p = p.left;
			}
		}
		return inOrder;
	}
}

非递归算法：
public ArrayList<Integer> inorderTraversal(TreeNode root) {  
    ArrayList<Integer> res = new ArrayList<Integer>();  
    if(root == null)  
        return res;  
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
    while(root!=null || !stack.isEmpty()){  
        if(root!=null){
            stack.push(root);
            root = root.left; 
        }else{  
            root = stack.pop();
            res.add(root.val); 
            root = root.right;  
        }  
    }  
    return res;  
}


递归算法：

   public void helper(TreeNode root, ArrayList<Integer> re){
        if(root==null)
            return;
        helper(root.left,re);
        re.add(root.val);
        helper(root.right,re);
    }
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(root==null)
            return re;
        helper(root,re);
        return re; 
    }
	
	
	
	
	
	
	
	
	
	
	
	