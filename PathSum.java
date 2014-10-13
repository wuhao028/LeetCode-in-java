

/** 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \      \
 *       7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
 
//递归解法
public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.left == null && root.right == null && root.val == sum)
			return true;
		boolean l = root.left != null ? hasPathSum(root.left, sum - root.val)
				: false;
		boolean r = root.right != null ? hasPathSum(root.right, sum - root.val)
				: false;
		return l || r;
	}
}
// 递归解法
public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) 
            return false;
        
        sum -= root.val;
        if(root.left == null && root.right==null)  
            return sum == 0;
        else 
            return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
    }



// 非递归解法，
// 我一开始写的就是把递归和非递归的两种解法揉在了一起~~~囧~


public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
 
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> values = new LinkedList<Integer>();
 
        nodes.add(root);
        values.add(root.val);
 
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();
 
            if(curr.left == null && curr.right == null && sumValue==sum){
                return true;
            }
 
            if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue+curr.left.val);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue+curr.right.val);
            }
        }
 
        return false;
    }