package algorithms.MergeIntervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms{
    /**
     * Naive approach: time: O(n2) space: O(n)
     * step1: Sort the input intervals according to their starting times
     * step2: Initialize a boolean array to store whether the current intervals had arrange meeting
     * step3: Traverse the input intervals
     *          - If the room is arranged, continue;
     *          - If current intervals.start < previous intervals.end and room is not arranged, update the end time of previous room and modify the room is been arranged
     * step4: add the number to answer
     *
     * @param meetingTimes
     * @return
     */
    public static int minMeetingRooms(List <Interval> meetingTimes) {
        int length = meetingTimes.size();
        if(length < 2){
            return length;
        }
        //step1
        meetingTimes.sort(Comparator.comparingInt(Interval::getStart));
        //step2
        boolean[] isRoomArranged = new boolean[length];
        int ans = 0;

        for(int i = 0; i < length; i++){
            if(isRoomArranged[i]){
                continue;
            }
            int prevEnd = meetingTimes.get(i).getEnd();
            for(int j = i + 1; j < length; j++){
                Interval curr = meetingTimes.get(j);
                if(curr.getStart() >= prevEnd && !isRoomArranged[j]){
                    isRoomArranged[j] = true;
                    prevEnd = curr.getEnd();
                }
            }
            ans++;
        }


        return ans;
    }

    /**
     * Optimize: min heap, time: O(n), space: O(n)
     *     step1: Sort the input intervals according to their starting time
     *     step2: Initialize a min heap and add the end time of the first meeting
     *     step3: Traverse the remaining intervals
     *              - If start time of current intervals greater than the minimum end time of previous intervals(root of heap), poll the minimum end time
     *              - add the end time of current intervals to heap(update the minimum end time of previous intervals and the end time of current intervals)
     *
     * @param meetingTimes
     * @return
     */
    public static int minMeetingRoomsHeap(List <Interval> meetingTimes) {
        int length = meetingTimes.size();
        if(length < 2){
            return length;
        }
        meetingTimes.sort(Comparator.comparingInt(Interval::getStart));
        PriorityQueue<Integer> allocator = new PriorityQueue<>(meetingTimes.size());
        allocator.add(meetingTimes.get(0).getEnd());
        for (int i = 1; i < length; i++) {
            if(meetingTimes.get(i).getStart() >= allocator.peek()){
                allocator.poll();
            }
            allocator.add(meetingTimes.get(i).getEnd());
        }

        return allocator.size();
    }

    public static void main(String[] args) {
        List<Interval> existingIntervals = Arrays.asList(
                new Interval(2, 8),
                new Interval(3, 4),
                new Interval(3, 9),
                new Interval(5, 11),
                new Interval(8, 20),
                new Interval(11, 15)
        );
        System.out.println("rooms: " + minMeetingRoomsHeap(existingIntervals));
    }
}