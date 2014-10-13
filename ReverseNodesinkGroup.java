

/** 
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * 
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */

public class ReverseNodesinkGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null)
			return null;
		int t = 0;
		ListNode nextHead = head;
		while (nextHead != null && t < k - 1) {
			nextHead = nextHead.next;
			t++;
		}
		if (nextHead == null || t < k - 1) {
			return head;
		} else {
			ListNode nextPart = reverseKGroup(nextHead.next, k);
			return reverseList(head, k, nextPart);
		}
	}

	private ListNode reverseList(ListNode head, int k, ListNode nextPart) {
		ListNode cur = head, prev = null, next = null;
		for (int i = 0; i < k; i++) {
			next = cur.next;
			if (i != 0) {
				cur.next = prev;
			} else {
				cur.next = nextPart;
			}
			prev = cur;
			cur = next;
		}
		return prev;
	}
}
解法一：比较挫的解法。要判断很多分支，所以容易出错。先放在这里当反面教材。


复制代码
 1 public static ListNode reverseKGroup(ListNode head, int k) {
 2         // Start typing your Java solution below
 3         // DO NOT write main() function
 4         ListNode firstGHead = head;
 5         int idx = 0;
 6         ListNode pp = null;
 7         while(head != null) {
 8             ListNode c = head;
 9             if(k <= 1 || c == null)return firstGHead;
10             for(int i = 0; i < k - 1&& c != null;i++) {
11                 c = c.next;
12             }
13             if(c == null) break; 
14             c = head;//save original head
15             int i = k - 1;
16             ListNode p = head;
17             ListNode pn = p.next;
18             ListNode lastEnd = pp;
19             while(i > 0){
20                 p = head;
21                 head = head.next;
22                 pn = p.next;
23                 pp = lastEnd;
24                 int swap = 0;
25                 while(swap < i){
26                     p.next = pn.next;
27                     pn.next = p;
28                     if(pp != null)
29                         pp.next = pn;
30                     pp = pn;
31                     pn= p.next;
32                     swap++;
33                 }
34                 i--;
35             }
36             if(idx++ == 0) firstGHead = head;
37             head = c.next;
38             pp = c;
39         }
40         return firstGHead;
复制代码
逆转那部分惨不忍睹啊。。。虽然过了leetcode，但是很不simple。还有，如果写成递归leetcode是不让你过的。

解法二：比较好的解法，又是leetcode讨论组的人做的。首先，搞清楚怎么逆转一个单链表。其实O(n)就可以了。第一个肯定是last one。然后我们每遍历到一个node，就把它放到最链表的首位，最后一个么，最后就成为第一个了。下面是一个简单逆转链表的程序。


复制代码
 1 ListNode dummy = new ListNode(0);
 2         dummy.next = head;
 3         ListNode pre = dummy;
 4         ListNode cur = head.next;
 5         ListNode last = head;
 6         while(cur != null){
 7             last.next = cur.next;
 8             cur.next = pre.next;
 9             pre.next = cur;
10             cur = last.next;
11         }
12         head = dummy.next;
复制代码
因为有“放到链表首位”的操作，我们需要一个dummy的头节点，遇到的新节点我们simply state: pre.next = cur; 保持一个invariant就是last节点始终在最后（cur的前面一个）

然后我们有如下方法：


复制代码
/**
     * Reverse a link list between pre and next exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |   
     * pre        next
     * after call pre = reverse(pre, next)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     * @param pre 
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */
    private static ListNode reverse(ListNode pre, ListNode next){
        ListNode last = pre.next;//where first will be doomed "last"
        ListNode cur = last.next;
        while(cur != next){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
复制代码
就是区间的reverse。因为题目要求的是k group逆转嘛。注意人返回的是最后一个(last)节点，这样下一个k-group就可以用上了。牛人的想法真是周到体贴～～。主方法里面，遍历的过程中每次都计数，每次到达k个节点，就可以使用pre和head.next调用上面的方法逆转了。给跪了。


复制代码
 1 public static ListNode reverseKGroup2(ListNode head, int k) {
 2         if(head == null || k == 1) return head;
 3         ListNode dummy = new ListNode(0);
 4         dummy.next = head;
 5         ListNode pre = dummy;
 6         int i = 0;
 7         while(head != null){
 8             i++;
 9             if(i % k ==0){
10                 pre = reverse(pre, head.next);
11                 head = pre.next;
12             }else {
13                 head = head.next;
14             }
15         }
16         return dummy.next;
17     }
复制代码