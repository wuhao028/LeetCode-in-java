/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *      1
 *     / \
 *    /   \
 *   0 --- 2
 *        / \
 *        \_/
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return node;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.add(node);
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		while (!queue.isEmpty()) {
			UndirectedGraphNode n = queue.remove();
			if (visited.contains(n))
				continue;
			visited.add(n);
			UndirectedGraphNode clone;
			if (!map.containsKey(n)) {
				clone = new UndirectedGraphNode(n.label);
				map.put(n, clone);
			} else {
				clone = map.get(n);
			}
			for (UndirectedGraphNode child : n.neighbors) {
				queue.add(child);
				UndirectedGraphNode cloneChild;
				if (!map.containsKey(child)) {
					cloneChild = new UndirectedGraphNode(child.label);
					map.put(child, cloneChild);
				} else {
					cloneChild = map.get(child);
				}
				clone.neighbors.add(cloneChild);
			}
		}
		return map.get(node);
	}
}



题解：
这道题考察对图的遍历和利用HashMap拷贝的方法。
对图的遍历就是两个经典的方法DFS和BFS。BFS经常用Queue实现，DFS经常用递归实现（可改为栈实现）。
拷贝方法是用用HashMap，key存原始值，value存copy的值，用DFS,BFS方法遍历帮助拷贝neighbors的值。

先复习下DFS和BFS。

DFS（Dpeth-first Search）
顾名思义，就是深度搜索，一条路走到黑，再选新的路。
记得上Algorithm的时候，教授举得例子就是说，DFS很像好奇的小孩，你给这个小孩几个盒子套盒子，好奇的小孩肯定会一个盒子打开后继续再在这个盒子里面搜索。
等把这一套盒子都打开完，再打开第二套的。
Wikipedia上的讲解是：“Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. 
One starts at the root (selecting some arbitrary node as the root in the case of a graph) and explores as far as possible 
along each branch before backtracking.”
通常来说简便的DFS写法是用递归，如果非递归的话就是栈套迭代，思想是一样的。
递归写法的DFS伪代码如下：
Input: A graph G and a root v of G
1   procedure DFS(G,v):
2       label v as discovered
3       for all edges from v to w in G.adjacentEdges(v) do
4           if vertex w is not labeled as discovered then
5               recursively call DFS(G,w)
非递归写法的DFS伪代码如下：
Input: A graph G and a root v of G

procedure DFS-iterative(G,v):
      let S be a stack
      S.push(v)
      while S is not empty
            v ← S.pop() 
            if v is not labeled as discovered:
                label v as discovered
                for all edges from v to w in G.adjacentEdges(v) do
                    S.push(w)


BFS（Breadth-first Search）
这个就是相对于BFS的另外一种对图的遍历方法，对于一个节点来说先把所有neighbors都检查一遍，再从第一个neighbor开始，循环往复。
由于BFS的这个特质，BFS可以帮助寻找最短路径。
Wikipedia上面对BFS的定义是：
“In graph theory, breadth-first search (BFS) is a strategy for searching in a graph
 when search is limited to essentially two operations: (a) visit and 
inspect a node of a graph; (b) gain access to visit the nodes that 
neighbor the currently visited node. The BFS begins at a root node and 
inspects all the neighboring nodes. Then for each of those neighbor 
nodes in turn, it inspects their neighbor nodes which were unvisited, 
and so on. Compare BFS with the equivalent, but more memory-efficient 
Iterative deepening depth-first search and contrast with depth-first search.”

通常BFS用queue+循环实现，伪代码如下：
Input: A graph G and a root v of G
procedure BFS(G,v) is
      create a queue Q
      create a set V
      add v to V
      enqueue v onto Q
      while Q is not empty loop
         t ← Q.dequeue()
         if t is what we are looking for then
            return t
        end if
        for all edges e in G.adjacentEdges(t) loop
           u ← G.adjacentVertex(t,e)
           if u is not in V then
               add u to V
               enqueue u onto Q
           end if
        end loop
     end loop
     return none
 end BFS
********************************************************************************************************************************
下面就是这道题的3种解题方法。


第一种实现方法是BFS的，就是先将头节点入queue，每一次queue出列一个node，然后检查这个node的所有的neighbors，如果没visited过，就入队，并更新neighbor。
然后更新新的neighbor列表。
代码如下：




public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
            
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        hm.put(node, head);
        queue.add(node);
        
        while(!queue.isEmpty()){
            UndirectedGraphNode curnode = queue.poll();
            for(UndirectedGraphNode aneighbor: curnode.neighbors){//check each neighbor
                if(!hm.containsKey(aneighbor)){//if not visited,then add to queue
                    queue.add(aneighbor);
                    UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
                    hm.put(aneighbor, newneighbor);
                }
                
                hm.get(curnode).neighbors.add(hm.get(aneighbor));
            }
        }
        
        return head;
    }
	
	
DFS的递归操作如下，迭代复制neighbors：


public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
            
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        hm.put(node, head);
        
        DFS(hm, node);//DFS
        return head;
    }
    public void DFS(HashMap<UndirectedGraphNode, UndirectedGraphNode> hm, UndirectedGraphNode node){
        if(node == null)
            return;
            
        for(UndirectedGraphNode aneighbor: node.neighbors){ 
            if(!hm.containsKey(aneighbor)){
                UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
                hm.put(aneighbor, newneighbor);
                DFS(hm, aneighbor);//DFS
            }
            hm.get(node).neighbors.add(hm.get(aneighbor));
        }
    }

下面一种方法是DFS的非递归方法，中点是把BFS中的queue换成stack，因为出列方法不一样了，所以遍历的线路就不一样了。代码如下:

public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
            
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        hm.put(node, head);
        stack.push(node);
        
        while(!stack.isEmpty()){
            UndirectedGraphNode curnode = stack.pop();
            for(UndirectedGraphNode aneighbor: curnode.neighbors){//check each neighbor
                if(!hm.containsKey(aneighbor)){//if not visited,then push to stack
                    stack.push(aneighbor);
                    UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
                    hm.put(aneighbor, newneighbor);
                }
                
                hm.get(curnode).neighbors.add(hm.get(aneighbor));
            }
        }
        
        return head;
    }
	
	
	
	
	
	
	
	
	
	