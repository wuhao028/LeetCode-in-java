import java.util.ArrayList;
import java.util.List;



/** 
 * Populating Next Right Pointers in Each Node IIOct 28 '12
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *        1
 *       /  \
 *      2    3
 *     / \    \
 *    4   5    7
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *       /  \
 *      2 -> 3 -> NULL
 *     / \    \
 *    4-> 5 -> 7 -> NULL
 */
	
public class PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		TreeLinkNode curLevel = root, cur = root;
		curLevel.next = null;
		List<TreeLinkNode> nextLevelNodes = new ArrayList<TreeLinkNode>();
		while (true) {
			while (cur != null) {
				if (cur.left != null) {
					nextLevelNodes.add(cur.left);
				}
				if (cur.right != null) {
					nextLevelNodes.add(cur.right);
				}
				cur = cur.next;
			}
			for (int i = 0; i < nextLevelNodes.size() - 1; i++) {
				nextLevelNodes.get(i).next = nextLevelNodes.get(i + 1);
			}
			if (nextLevelNodes.size() > 0) {
				curLevel = nextLevelNodes.get(0);
				cur = curLevel;
				nextLevelNodes.clear();
			} else {
				break;
			}
		}
	}
}



递归解法：

public void connect(TreeLinkNode root) {  
        if (root == null) 
            return;  
  
        TreeLinkNode p = root.next;  
        /*
		这道题跟I的区别就是binary tree不是完全二叉树。
        所以root.right.next就不一定等于root.next.left。
        所以，目标就是先确定好root的右孩子的第一个有效next连接点，然后再处理左孩子。
        因此，这道题目首要是找到右孩子的第一个有效的next链接节点，然后再处理左孩子。然后依次递归处理右孩子，左孩子
        */
        while (p != null) {  
            if (p.left != null) {  
                p = p.left;  
                break;  
            }  
            if (p.right != null) {  
                p = p.right;  
                break;  
            }  
            p = p.next;  
        }  
  
        if (root.right != null) {  
            root.right.next = p;  
        }  
  
        if (root.left != null) {
            if(root.right!=null)
                root.left.next = root.right;
            else
                root.left.next = p;
        }  
  
        connect(root.right);
        connect(root.left);
    }
