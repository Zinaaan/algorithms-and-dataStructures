package algorithms.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Intersection {
    // Function to find the intersecting points between two intervals
    //[[1,4],[5,6],[7,8],[9,15]]
    //[[2,4],[5,7],[9,15]]

    /**
     * step1: Initialize a array list to store all intervals
     * step2: Nested for loops of intervalListA and intervalListB, move index of IntervalA to the position if IntervalA[0] < IntervalB[0]
     * - if IntervalA[1] < intervalB[0], continue;
     * - else if IntervalA[1] == intervalB[0], add [IntervalA[1], intervalB[0]]
     * - else if IntervalA[1] > intervalB[0]
     * - if IntervalA[1] >= intervalB[1], add [IntervalB[0], intervalB[1]]
     * - else if IntervalA[1] < intervalB[1], add[IntervalB[0], IntervalA[1]]
     *
     * @param intervalListA
     * @param intervalListB
     * @return
     */
    public static List<Interval> intervalsIntersection(List<Interval> intervalListA, List<Interval> intervalListB) {
        List<Interval> intersections = new ArrayList<>();
        int i = 0, j = 0;
        while (i < intervalListA.size() && j < intervalListB.size()){
            Interval intervalA = intervalListA.get(i);
            Interval intervalB = intervalListB.get(j);
            int start = Math.max(intervalA.getStart(), intervalB.getStart());
            int end = Math.min(intervalA.getEnd(), intervalB.getEnd());
            if(start <= end){
                intersections.add(new Interval(start, end));
            }
            if(intervalA.getEnd() < intervalB.getEnd()){
                i++;
            } else if (intervalA.getEnd() > intervalB.getEnd()){
                j++;
            } else {
                i++;
                j++;
            }
        }
        return intersections;
    }

    public static String display(List<Interval> l1) {
        String resultStr = "[";
        for (int i = 0; i < l1.size() - 1; i++) {
            resultStr += "[" + l1.get(i).getStart() + ", " + l1.get(i).getEnd() + "], ";
        }
        resultStr += "[" + l1.get(l1.size() - 1).getStart() + ", " + l1.get(l1.size() - 1).getEnd() + "]";
        resultStr += "]";

        return resultStr;
    }

    public static void main(String args[]) {
        List<List<Interval>> inputIntervalLista = Arrays.asList(
                Arrays.asList(new Interval(1, 4),
                        new Interval(5, 6),
                        new Interval(9, 15)),
                Arrays.asList(new Interval(3, 6),
                        new Interval(8, 16),
                        new Interval(17, 25)),
                Arrays.asList(new Interval(4, 7),
                        new Interval(9, 16),
                        new Interval(17, 28),
                        new Interval(39, 50),
                        new Interval(55, 66),
                        new Interval(70, 89)),
                Arrays.asList(new Interval(1, 3),
                        new Interval(5, 6),
                        new Interval(7, 8),
                        new Interval(12, 15))
        );
        List<List<Interval>> inputIntervalListb = Arrays.asList(
                Arrays.asList(new Interval(2, 4),
                        new Interval(5, 7),
                        new Interval(9, 15)),
                Arrays.asList(new Interval(2, 3),
                        new Interval(10, 15),
                        new Interval(18, 23)),
                Arrays.asList(new Interval(3, 6),
                        new Interval(7, 8),
                        new Interval(9, 10),
                        new Interval(14, 19),
                        new Interval(23, 33),
                        new Interval(35, 40),
                        new Interval(45, 59),
                        new Interval(60, 64),
                        new Interval(68, 76)),
                Arrays.asList(new Interval(2, 4),
                        new Interval(7, 10))
        );

        for (int i = 0; i < inputIntervalLista.size(); i++) {
            System.out.println(i + 1 + ".\t Interval List A: " + display(inputIntervalLista.get(i)));
            System.out.println("\t Interval List B: " + display(inputIntervalListb.get(i)));
            System.out.println("\t Intersecting intervals in 'A' and 'B' are: " + display(intervalsIntersection(
                    inputIntervalLista.get(i), inputIntervalListb.get(i))));
        }
    }
}