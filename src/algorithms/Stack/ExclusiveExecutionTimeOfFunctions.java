package algorithms.Stack;

import java.util.*;

class ExclusiveExecutionTimeOfFunctions {

    public static List<Integer> exclusiveTime(int n, List<String> events) {
        Deque<Event> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        for (String content : events) {
            // Extract the log details from the content(string)
            Event event = new Event(content);
            if (event.getIsStart()) {
                // Push the event details to the stack
                stack.push(event);
            } else {
                // Pop the log details from the stack
                Event top = stack.pop();
                // Add the execution time of the current function in the actual result
                System.out.println("event: " + (event.getTime() - top.getTime() + 1));
                result.set(top.getId(), result.get(top.getId()) + (event.getTime() - top.getTime() + 1));
                // If the stack is not empty, subtract the current child function execution time from the parent function
                if (!stack.isEmpty()) {
                    result.set(stack.peek().getId(), result.get(stack.peek().getId()) - (event.getTime() - top.getTime() + 1));
                }
                System.out.println(result);
            }
        }
        return result;
    }

    public static void main(String args[]) {
        List<List<String>> events = Arrays.asList(
                Arrays.asList("0:start:0", "1:start:2", "1:end:3", "2:start:4", "2:end:7", "0:end:8"),
                Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"),
                Arrays.asList("0:start:0", "1:start:5", "1:end:6", "0:end:7"),
                Arrays.asList("0:start:0", "1:start:5", "2:start:8", "3:start:12", "4:start:15", "5:start:19", "5:end:22", "4:end:24", "3:end:27", "2:end:32", "1:end:35", "0:end:36"),
                Arrays.asList("0:start:0", "1:start:3", "1:end:6", "0:end:10")
        );
        List<Integer> n = Arrays.asList(3, 2, 2, 6, 2);
        int x = 1;
        for (int i = 0; i < n.size(); i++) {
//            System.out.println(x + ".\tn = " + n.get(i));
//            System.out.println("\tevents = " + events.get(i));
            System.out.println("\tOutput: " + exclusiveTime(n.get(i), events.get(i)));
            x += 1;
        }
    }
}