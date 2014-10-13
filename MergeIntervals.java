import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/** 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

public class MergeIntervals {
	public class IntervalCmp implements Comparator<Interval> {
		@Override
		public int compare(Interval i1, Interval i2) {
			if (i1.start < i2.start) return -1;
			if (i1.start == i2.start && i1.end <= i2.end) return -1;
			return 1;
		}
	}
	
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		ArrayList<Interval> ret = new ArrayList<Interval>();
		if (intervals.size() == 0) return ret;
		Interval[] arr = new Interval[intervals.size()];
		intervals.toArray(arr);
		Arrays.sort(arr, new IntervalCmp());
		int start = arr[0].start;
		int end = arr[0].end;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].start <= end) {
				end = Math.max(end, arr[i].end);
			} else {
				ret.add(new Interval(start, end));
				start = arr[i].start;
				end = arr[i].end;
			}
		}
		ret.add(new Interval(start, end));
		return ret;
	}
}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
   public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
 
        // sort intervals by using self-defined Comparator
        Collections.sort(intervals, new IntervalComparator());
        
        ArrayList<Interval> result = new ArrayList<Interval>();
 
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
 
            if (prev.end >= curr.start) {
                // merged case
                Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = merged;
            } else {
                result.add(prev);
                prev = curr;
            }
        }
 
        result.add(prev);
 
        return result;
        }
    }
 
    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
}




