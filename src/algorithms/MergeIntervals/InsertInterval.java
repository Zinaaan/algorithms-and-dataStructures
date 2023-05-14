package algorithms.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InsertInterval {

    //  public static List<Interval> insertInterval(List <Interval> existingIntervals, Interval newInterval) {
//    int newStart = newInterval.getStart();
//    int newEnd = newInterval.getEnd();
//    int i = 0;
//    int n = existingIntervals.size();
//    List <Interval> output = new ArrayList<Interval>();
//    while (i < n && existingIntervals.get(i).getStart() < newStart) {
//      output.add(existingIntervals.get(i));
//      i += 1;
//    }
//    if (output.size() == 0 || output.get(output.size() - 1).getEnd() < newStart) {
//      output.add(newInterval);
//    }
//    else output.get(output.size() - 1).setEnd(Math.max(output.get(output.size() - 1).getEnd(), newEnd));
//    while (i < n) {
//      Interval ei = existingIntervals.get(i);
//      int start = ei.getStart();
//      int end = ei.getEnd();
//      if (output.get(output.size() - 1).getEnd() < start) output.add(ei);
//      else output.get(output.size() - 1).setEnd(Math.max(output.get(output.size() - 1).getEnd(), end));
//      i += 1;
//    }
//    return output;
//  }
//
    public static String display(List<Interval> l1) {
        String resultStr = "[";
        for (int i = 0; i < l1.size() - 1; i++) {
            resultStr += "[" + l1.get(i).getStart() + ", " + l1.get(i).getEnd() + "], ";
        }
        resultStr += "[" + l1.get(l1.size() - 1).getStart() + ", " + l1.get(l1.size() - 1).getEnd() + "]";
        resultStr += "]";

        return resultStr;
    }

    /**
     * step1:
     *
     * @param existingIntervals
     * @param newInterval
     * @return
     */
    private static List<Interval> insertInterval(List<Interval> existingIntervals, Interval newInterval) {
        int length = existingIntervals.size();
        if (length < 2) {
            return existingIntervals;
        }
        int start = newInterval.getStart();
        int end = newInterval.getEnd();
        int i = 0;
        List<Interval> ans = new ArrayList<>();
        while (i < length && start > existingIntervals.get(i).getStart()) {
            ans.add(existingIntervals.get(i));
            i++;
        }
        if (ans.size() == 0 || ans.get(ans.size() - 1).getEnd() < start) {
            ans.add(newInterval);
        } else {
            ans.get(ans.size() - 1).setEnd(Math.max(end, ans.get(ans.size() - 1).getEnd()));
        }
        while(i < length){
            Interval prev = ans.get(ans.size() - 1);
            Interval curr = existingIntervals.get(i);
            if(prev.getEnd() >= curr.getStart()){
                prev.setEnd(Math.max(prev.getEnd(), curr.getEnd()));
            } else {
                ans.add(curr);
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Interval newInterval = new Interval(5, 7);
        List<Interval> existingIntervals = Arrays.asList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 8));
        List<Interval> output = insertInterval(existingIntervals, newInterval);
        System.out.println("Existing intervals: " + display(existingIntervals));
        System.out.println("New interval: [" + newInterval.getStart() + ", " + newInterval.getEnd() + "]");
        System.out.println("\nNew intervals list: " + display(output));
    }

}