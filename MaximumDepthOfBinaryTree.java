/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

import java.util.LinkedList;
import java.util.Queue;

// 非递归
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
    	int ret = 1;
    	int currentLevel = 1;
    	int nextLevel = 0;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.remove();
    		currentLevel--;
    		if (node.left != null) {
    			queue.add(root.left);
    			nextLevel++;
    		}
    		if (node.right != null) {
    			queue.add(root.right);
    			nextLevel++;
    		}
    		if (currentLevel == 0) {
    			if (nextLevel != 0) {
    				ret++;
    			}
    			currentLevel = nextLevel;
    			nextLevel = 0;
    		}
    	}
    	return ret;
    }
}



//递归

    public int maxDepth(TreeNode root) {
        return max(root,0);
    }
    
    int max=Integer.MIN_VALUE;
    
    public int max(TreeNode root,int num){
        
        if(root==null){
        max=Math.max(max,num);
        }
        
        if(root!=null){
            
            max(root.right,num+1);
            max(root.left,num+1);
            
        }
 
    return max;
 
    }
    
// 递归
	public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        int leftmax = maxDepth(root.left);
        int rightmax = maxDepth(root.right);
        return Math.max(leftmax, rightmax)+1;
    }
    
