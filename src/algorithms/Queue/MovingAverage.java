package algorithms.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzn
 * @date 2023/03/07 14:23
 * @description
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverage {

    /**
     * step1: Initialize a Queue to store current val
     * step2: Initialize a sum to calculate the each round of the next
     *
     * @param size
     */
    private Deque<Integer> queue;
    int sum;
    int capacity;
    public MovingAverage(int size) {
        queue = new ArrayDeque<>();
        sum = 0;
        capacity = size;
    }

    public double next(int val) {
        if(capacity == queue.size()){
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return (double) sum / queue.size();
    }
}
