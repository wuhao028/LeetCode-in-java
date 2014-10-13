

/**
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white,
 * and blue respectively.
 * 
 * Note: 
 * 
 * You are not suppose to use the library's sort function for this problem.
 * 
 * Follow up: 
 * A rather straight forward solution is a two-pass algorithm using counting sort. 
 * 
 * First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * 
 * Could you come up with an one-pass algorithm using only constant space?
 */

public class SortColors {
	public void sortColors(int[] A) {
		int length = A.length;
		int left = -1;
		int right = length;
		int i = 0;
		while (i < right) {
			if (A[i] == 0) {
				swap(A, ++left, i++);
			} else if (A[i] == 2) {
				swap(A, i, --right);
			} else {
				i++;
			}
		}
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}




这道题就是运用指针来解决了，可以说叫3指针吧。

一个指针notred从左开始找，指向第一个不是0（红色）的位置；一个指针notblue从右开始往左找，指向第一个不是2（蓝色）的位置。

然后另一个新的指针i指向notred指向的位置，往后遍历，遍历到notred的位置。

这途中需要判断：

当i指向的位置等于0的时候，说明是红色，把他交换到notred指向的位置，然后notred++，i++。

当i指向的位置等于2的时候，说明是蓝色，把他交换到notblue指向的位置，然后notred--。

当i指向的位置等于1的时候，说明是白色，不需要交换，i++即可。
public static void swap(int A[], int i, int j){
        int tmp = A[i];
        A[i]=A[j];
        A[j]=tmp;
    }
    
    public static void sortColors(int A[]) {
        if(A == null || A.length==0)
            return;
            
        int notred=0;
        int notblue=A.length-1;
         
        while (notred<A.length&&A[notred]==0)
            notred++;
            
        while (notblue>=0&&A[notblue]==2)
            notblue--;
         
        int i=notred;
        while (i<=notblue){
            if (A[i]==0) {
                swap(A,i,notred);
                notred++;
                i++;
            }else if (A[i]==2) {
                swap(A,i,notblue);
                notblue--;
            }else
                i++;
        }
    }