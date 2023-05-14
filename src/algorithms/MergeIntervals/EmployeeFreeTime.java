package algorithms.MergeIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeFreeTime {

    /**
     * Question:
     * Given a person's work hours and an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],…] (si < ei), print out the free time of this person. For example,
     * work hour: [0, 45]
     * meeting time: [3, 30],[5, 10],[15, 20]
     * free time: [0,3],[30,45]
     **/

    private static List<Interval> intervals(int... times) {
        List<Interval> intervals = new ArrayList<>(times.length / 2);
        for (int i = 0; i < times.length; i += 2) {
            intervals.add(new Interval(times[i], times[i + 1]));
        }
        return intervals;
    }

    public static void main(String[] args) {
//        List<Interval> expected = intervals(0, 3, 30, 45);
        List<Interval> expected = intervals(0, 3, 30, 45);
        System.out.println("Expected: " + expected);
//        List<Interval> workHours = intervals(3, 30, 5, 10, 15, 20);
        List<Interval> workHours = intervals(1, 15, 5, 10, 15, 20);
        List<Interval> freeTime = employeeFreeTime(new Interval(0, 45), workHours);
        System.out.println("Actual:" + freeTime);
    }
//
//    static class Interval {
//        //from https://leetcode.com/problems/employee-free-time/
//        public int start;
//        public int end;
//
//        public Interval() {
//        }
//
//        public Interval(int _start, int _end) {
//            start = _start;
//            end = _end;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("[%d, %d]", start, end);
//        }
//
//    }

    static List<Interval> employeeFreeTime(Interval workHours, List<Interval> meetings) {
        if (workHours == null || meetings == null || meetings.isEmpty()) {
            return Collections.emptyList();
        }
        List<Interval> freeTime = new ArrayList<>();
        //Sort array of meetings by start time
        meetings.sort(Comparator.comparingInt(Interval::getStart));
        int prevEnd = workHours.getStart();
        for (Interval curr : meetings) {
            //current interval have no overlap with previous interval, it means [previous.end, current.start] is the free time
            if (curr.getStart() > prevEnd) {
                freeTime.add(new Interval(prevEnd, curr.getStart()));
            }
            //update the max end time of meeting
            prevEnd = Math.max(prevEnd, curr.getEnd());
        }
        //if [prevEnd, workHours.getEnd()] == [30, 40], we need add [30, 40] to freeTime
        if(prevEnd < workHours.getEnd()){
            freeTime.add(new Interval(prevEnd, workHours.getEnd()));
        }
        return freeTime;
    }

//    static List<Interval> EmployeeFreeTime(Interval workHours, List<Interval> meetings) {
//        if (workHours == null || meetings == null || meetings.isEmpty()) {
//            return Collections.emptyList();
//        }
//        Queue<Interval> blockedTime = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));
//        blockedTime.add(new Interval(workHours.end, Integer.MAX_VALUE));
//        blockedTime.add(new Interval(-1, workHours.start));
//        blockedTime.addAll(meetings);
//        List<Interval> freeTime = new LinkedList<>();
//        while (blockedTime.size() > 1) {
//            Interval first = blockedTime.poll();
//            Interval second = blockedTime.peek();
//            if (first.end < second.start) {
//                freeTime.add(new Interval(first.end, second.start));
//            } else if (first.end > second.end) {
//                blockedTime.poll();
//                blockedTime.offer(first);
//            }
//        }
//        return freeTime;
//    }

}