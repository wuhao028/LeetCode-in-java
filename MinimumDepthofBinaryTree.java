

/** 
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node. 
 */

public class MinimumDepthofBinaryTree {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		else {
			int leftDepth = root.left != null ? minDepth(root.left)
					: Integer.MAX_VALUE;
			int rightDepth = root.right != null ? minDepth(root.right)
					: Integer.MAX_VALUE;
			return Math.min(leftDepth, rightDepth) + 1;
		}
	}
}

// my version WRONG!!!  again把递归非递归揉在了一起，这样写只保存了一个depth，
//在不断增大
// 所以它只能保存最大的路劲，而不是最小的！

    public class Solution {
    
    int min=Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        if(root==null)
         return 0;
         helper(root,1);
         return min;
    }
    
    public void helper(TreeNode root,int depth){
        if(root.left==null && root.right==null){
            if(depth<min)
              min=depth;
              return;
        }
            
        if(root.left!=null){
          depth++;
          helper(root.left,depth++);
        
        }
        
        
        if(root.right!=null){
            depth++;
            helper(root.right,depth);
          
        }
        
       
    } 

}


// 递归
public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        int minleft = minDepth(root.left);
        int minright = minDepth(root.right);
        if(minleft==0 || minright==0)
            return minleft>=minright?minleft+1:minright+1;
        return Math.min(minleft,minright)+1;
    }
	
	
// 非递归
	public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        int depth = 1;//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curnum = 1;
        int nextnum = 0;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            curnum--;
            
            if(cur.left == null && cur.right == null)
                return depth;
            
            if(cur.left != null){
               queue.add(cur.left);
               nextnum++;
            }
            
            if(cur.right != null){
                queue.add(cur.right);
                nextnum++;
            }
            
            if(curnum == 0){
                curnum = nextnum;
                nextnum = 0;
                depth++;
            }
        }
        return depth;
    }