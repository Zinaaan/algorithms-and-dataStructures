package algorithms.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class EmployeeFreeTimes {
    /**
     * step1: Add all intervals to a array list
     * step2: Sort each schedule in the list by starting time
     * step3: Iterate over the intervals, keeping tracking of the previous latest ending time
     * step4: If the starting time for any period occurs after previous latest ending time, then this is a free time for all employees and we can
     * add it into the result list
     *
     * @param schedule
     * @return
     */
//    public static List<Interval> EmployeeFreeTime(ArrayList<ArrayList<Interval>> schedule) {
//        List<Interval> ans = new ArrayList<>();
//        if (schedule == null || schedule.size() == 0 || schedule.size() == 1) {
//            return ans;
//        }
//        //step1
//        List<Interval> scheduleList = schedule.stream().flatMap(Collection::stream).sorted(Comparator.comparingInt(Interval::getStart)).collect(Collectors.toList());
//        //step2
//        Interval prev = scheduleList.get(0);
//        for (int i = 1; i < scheduleList.size(); i++) {
//            Interval curr = scheduleList.get(i);
//            if (curr.getStart() > prev.getEnd()) {
//                ans.add(new Interval(prev.getEnd(), curr.getStart()));
//            }
//            prev.setEnd(Math.max(prev.getEnd(), curr.getEnd()));
//        }
//
//        return ans;
//    }
    public static List<Interval> employeeFreeTime(ArrayList<ArrayList<Interval>> schedule) {
        // Initializing two lists
        List<Interval> ans = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();
        // Merging all the employee schedules into one list of intervals
        for (ArrayList<Interval> intervalArrayList : schedule) {
            intervals.addAll(intervalArrayList);
        }
        // Sorting all intervals
        intervals.sort(Comparator.comparingInt(Interval::getStart));
        // Initializing prev_end as the endpoint of the first interval
        int prevEnd = intervals.get(0).getEnd();
        // iterating through the intervals and adding the gaps we find to the answer list
        for (Interval interval : intervals) {
            if (interval.getStart() > prevEnd) {
                ans.add(new Interval(prevEnd, interval.getStart()));
            }
            // if the current interval's ending time is later than the current prev_end, update it
            prevEnd = Math.max(prevEnd, interval.getEnd());
        }
        return ans;
    }

    // Function for displaying interval list
    public static String display(List<Interval> l1) {
        if (l1.size() == 0) {
            return "[]";
        }
        String resultStr = "[";
        for (int i = 0; i < l1.size() - 1; i++) {
            resultStr += "[" + l1.get(i).getStart() + ", ";
            resultStr += l1.get(i).getEnd() + "], ";
        }
        resultStr += "[" + l1.get(l1.size() - 1).getStart() + ", ";
        resultStr += l1.get(l1.size() - 1).getEnd() + "]";
        resultStr += "]";

        return resultStr;
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        List<List<List<Interval>>> inputs1 = Arrays.asList(Arrays.asList(Arrays.asList(new Interval(1, 2), new Interval(5, 6)), Arrays.asList(new Interval(1, 3)), Arrays.asList(new Interval(4, 10))), Arrays.asList(Arrays.asList(new Interval(1, 3), new Interval(6, 7)), Arrays.asList(new Interval(2, 4)), Arrays.asList(new Interval(2, 5), new Interval(9, 12))), Arrays.asList(Arrays.asList(new Interval(2, 3), new Interval(7, 9)), Arrays.asList(new Interval(1, 4), new Interval(6, 7))), Arrays.asList(Arrays.asList(new Interval(3, 5), new Interval(8, 10)), Arrays.asList(new Interval(4, 6), new Interval(9, 12)), Arrays.asList(new Interval(5, 6), new Interval(8, 10))), Arrays.asList(Arrays.asList(new Interval(1, 3), new Interval(6, 9), new Interval(10, 11)), Arrays.asList(new Interval(3, 4), new Interval(7, 12)), Arrays.asList(new Interval(1, 3), new Interval(7, 10)), Arrays.asList(new Interval(1, 4)), Arrays.asList(new Interval(7, 10), new Interval(11, 12))), Arrays.asList(Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8)), Arrays.asList(new Interval(2, 3), new Interval(4, 5), new Interval(6, 8))), Arrays.asList(Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)), Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)), Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12)), Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6), new Interval(7, 8), new Interval(9, 10), new Interval(11, 12))));
        int i = 1;

        ArrayList<ArrayList<ArrayList<Interval>>> inputs = new ArrayList<>();
        for (int j = 0; j < inputs1.size(); j++) {
            inputs.add(new ArrayList<>());
            for (int k = 0; k < inputs1.get(j).size(); k++) {
                inputs.get(j).add(new ArrayList<>());
                for (int g = 0; g < inputs1.get(j).get(k).size(); g++) {
                    inputs.get(j).get(k).add(inputs1.get(j).get(k).get(g));
                }
            }
        }
        for (ArrayList<ArrayList<Interval>> input : inputs) {
            System.out.println(i + ".\tEmployee Schedules:");
            for (ArrayList<Interval> intervals : input) {
                System.out.println("\t\t" + display(intervals));
            }
            System.out.println("\tEmployees' free time " + display(employeeFreeTime(input)));
            i += 1;
        }
    }
}