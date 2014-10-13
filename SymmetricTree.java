import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *   2   2
 *   \   \
 *   3    3
 * Note:
 * 
 * Bonus points if you could solve it both recursively and iteratively.
 */

 //非递归算法：
 
 //【一】
public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root.left);
		s2.push(root.right);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode n1 = s1.pop();
			TreeNode n2 = s2.pop();
			if (n1 == null && n2 == null) {
				continue;
			} else if (n1 == null || n2 == null) {
				return false;
			} else if (n1.val != n2.val) {
				return false;
			} else {
				s1.push(n1.left);
				s1.push(n1.right);
				s2.push(n2.right);
				s2.push(n2.left);
			}
		}
		return true;
	}
}


//【二】
public boolean isSymmetric(TreeNode root) {
    if(root == null)
        return true;
    if(root.left == null && root.right == null)
        return true;
    if(root.left == null || root.right == null)
        return false;
    LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
    LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
    q1.add(root.left);
    q2.add(root.right);
    while(!q1.isEmpty() && !q2.isEmpty()){
        TreeNode n1 = q1.poll();
        TreeNode n2 = q2.poll();
        
        if(n1.val != n2.val)
            return false;
        if((n1.left == null && n2.right != null) || (n1.left != null && n2.right == null))
            return false;
        if((n1.right == null && n2.left != null) || (n1.right != null && n2.left == null))
            return false;
        
        if(n1.left != null && n2.right != null){
            q1.add(n1.left);
            q2.add(n2.right);
        }
        
        if(n1.right != null && n2.left != null){
            q1.add(n1.right);
            q2.add(n2.left);
        }            
    }
    return true;
}

//递归算法：
public boolean isSymmetricTree(TreeNode p,TreeNode q){
     if(p == null&&q == null)
        return true;
     if(p == null||q == null)
        return false;
     return (p.val == q.val) && isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
}

public boolean isSymmetric(TreeNode root) {
    if(root==null) 
        return true;
        
    return isSymmetricTree(root.left,root.right);
}

















