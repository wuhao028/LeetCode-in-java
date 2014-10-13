

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/** 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 
 *
 */
 //http://www.cnblogs.com/TenosDoIt/p/3673188.html
 
//使用PriorityQueue
public class MergekSortedLists {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null || lists.isEmpty())
			return null;

		Comparator<ListNode> comp = new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				if (o1.val > o2.val)
					return 1;
				return 0;
			}
		};
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(
				lists.size(), comp);
		for (ListNode node : lists) {
			if (node != null)
				heap.add(node);
		}
		ListNode head = null, cur = null;
		while (!heap.isEmpty()) {
			if (head == null) {
				head = heap.poll();
				cur = head;
			} else {
				cur.next = heap.poll();
				cur = cur.next;
			}
			if (cur.next != null)
				heap.add(cur.next);
		}
		return head;
	}
}
//分治  迭代 的方法

public ListNode mergeKLists(ArrayList<ListNode> lists) {  
        if(lists==null || lists.size()==0)  
            return null;  
        return MSort(lists,0,lists.size()-1);  
    }  
    
    public ListNode MSort(ArrayList<ListNode> lists, int low, int high){  
        if(low < high){  
            int mid = (low+high)/2;
            ListNode leftlist = MSort(lists,low,mid);
            ListNode rightlist = MSort(lists,mid+1,high);
            return mergeTwoLists(leftlist,rightlist);  
        }  
        return lists.get(low);  
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1==null)
                return l2;
            if(l2==null)
                return l1;
                
            ListNode l3;
            if(l1.val<l2.val){
                l3 = l1;
                l1 = l1.next;
            }else{
                l3 = l2;
                l2 = l2.next;
            }
            
            ListNode fakehead = new ListNode(-1);
            fakehead.next = l3;
            while(l1!=null&&l2!=null){
                if(l1.val<l2.val){
                    l3.next = l1;
                    l3 = l3.next;
                    l1 = l1.next;
                }else{
                    l3.next = l2;
                    l3 = l3.next;
                    l2 = l2.next;
                }
            }
            
            if(l1!=null)
                l3.next = l1;
            if(l2!=null)
                l3.next = l2;
            return fakehead.next;
        }
		
		
		
		
算法1：

最傻的做法就是先1、2合并，12结果和3合并，123结果和4合并，…,123..k-1结果和k合并，我们计算一下复杂度。

1、2合并，遍历2n个节点

12结果和3合并，遍历3n个节点

123结果和4合并，遍历4n个节点

…

123..k-1结果和k合并，遍历kn个节点

总共遍历的节点数目为n(2+3+…+k) = n*(k^2+k-2)/2, 因此时间复杂度是O(n*(k^2+k-2)/2) = O(nk^2)，代码如下：

/**
 * Definition for singly-linked list.
 * struct ListNode {
 * int val;
 * ListNode *next;
 * ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode *mergeKLists(vector<ListNode *> &lists) {
        if(lists.size() == 0)return NULL;
        ListNode*res = lists[0];
        for(int i = 1; i < lists.size(); i++)
            res = merge2list(res, lists[i]);
        return res;
    }
     
    ListNode *merge2list(ListNode *head1, ListNode*head2)
    {
        ListNode node(0), *res = &node;
        while(head1 && head2)
        {
            if(head1->val <= head2->val)
            {
                res->next = head1;
                head1 = head1->next;
            }
            else
            {
                res->next = head2;
                head2 = head2->next;
            }
            res = res->next;
        }
       if(head1)
            res->next = head1;
        else if(head2)
            res->next = head2;
        return node.next;
    }
};
算法2：利用分治的思想把合并k个链表分成两个合并k/2个链表的任务，一直划分，知道任务中只剩一个链表或者两个链表。可以很简单的用递归来实现。因此算法复杂度为T(k) = 2T(k/2) + O(nk),很简单可以推导得到算法复杂度为O（nklogk）

递归的代码就不贴了。下面是非递归的代码非递归的思想是（以四个链表为例）：                   本文地址

1、3合并，合并结果放到1的位置

2、4合并，合并结果放到2的位置

再把1、2合并（相当于原来的13 和 24合并）

/**
 * Definition for singly-linked list.
 * struct ListNode {
 * int val;
 * ListNode *next;
 * ListNode(int x) : val(x), next(NULL) {}
 * };
 */
 
class Solution {
public:
    ListNode *mergeKLists(vector<ListNode *> &lists) {
        int n = lists.size();
        if(n == 0)return NULL;
        while(n >1)
        {
            int k = (n+1)/2;
            for(int i = 0; i < n/2; i++)
                lists[i] = merge2list(lists[i], lists[i + k]);
            n = k;
        }
        return lists[0];
    }
     
    ListNode *merge2list(ListNode *head1, ListNode*head2)
    {
        ListNode node(0), *res = &node;
        while(head1 && head2)
        {
            if(head1->val <= head2->val)
            {
                res->next = head1;
                head1 = head1->next;
            }
            else
            {
                res->next = head2;
                head2 = head2->next;
            }
            res = res->next;
        }
        if(head1)
            res->next = head1;
        else if(head2)
            res->next = head2;
        return node.next;
    }
};
 

算法3：维护一个k个大小的最小堆，初始化堆中元素为每个链表的头结点，每次从堆中选择最小的元素加入到结果链表，再选择该最小元素所在链表的下一个节点加入到堆中。这样当堆为空时，所有链表的节点都已经加入了结果链表。元素加入堆中的复杂度为O（longk），总共有kn个元素加入堆中，因此，复杂度也和算法2一样是O（nklogk）

/**
 * Definition for singly-linked list.
 * struct ListNode {
 * int val;
 * ListNode *next;
 * ListNode(int x) : val(x), next(NULL) {}
 * };
 */
 
class Solution {
private:
struct cmp
{
    bool operator ()(const ListNode *a, const ListNode *b)
    {
            return a->val > b->val;
    }
};
public:
    ListNode *mergeKLists(vector<ListNode *> &lists) {
        int n = lists.size();
        if(n == 0)return NULL;
        ListNode node(0), *res = &node;
        priority_queue<ListNode*, vector<ListNode*>, cmp> que;
        for(int i = 0; i < n; i++)
            if(lists[i])
                que.push(lists[i]);
        while(!que.empty())
        {
            ListNode * p = que.top();
            que.pop();
            res->next = p;
            res = p;
             
            if(p->next)
                que.push(p->next);
        }
        return node.next;
    }
};
        
        
    