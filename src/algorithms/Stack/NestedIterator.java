package algorithms.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NestedIterator {
    /**
     * step1: Initialize a List to save all the numbers from the flattened nestedList
     * step2: Traverse the input list,
     * case1: If getList().size() == 0 and nestedInteger.getInteger() != null, add the current number to the array
     * case2: Else getList().size() > 0, get the nestedList, recurse the step2
     */
    int currentIndex;
    int capacity;
    List<Integer> flattenedList = new ArrayList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        currentIndex = 0;
        getNestedList(nestedList);
        capacity = flattenedList.size();
    }

    public void getNestedList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.getList().size() == 0 && nestedInteger.isFile()) {
                flattenedList.add(nestedInteger.getFile());
            } else {
                getNestedList(nestedInteger.getList());
            }
        }
    }

    public int next() {
        if (hasNext()) {
            return flattenedList.get(currentIndex++);
        }
        return 0;
    }

    public boolean hasNext() {
        return currentIndex < capacity;
    }

    // ------ Please don't change the following function ----------
    // flatten_list function is used for testing porpuses.
    // Your code will be tested using this function
    public static List<Integer> flattenList(NestedIterator obj) {
        List<Integer> result = new ArrayList<>();
        while (obj.hasNext()) {
            result.add(obj.next());
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList("[1, [2, 3], 4]",
                "[3, [2, 3, 4], 4, [2, 3]]", "[[2, 3], 3, [2, 3], 4, [2, 3, 4, 5]]",
                "[1, [3, [4, [5, 6], 7], 8], 9]", "[[2, 3, [2, 3]]]");
        List<NestedIterator> itr = new ArrayList<>();
        // DeadLock Case 1
        List<NestedInteger> nestedList = new ArrayList<NestedInteger>();
        NestedInteger l1 = new NestedInteger();
        nestedList.add(new NestedInteger(1));
        l1.add(new NestedInteger(2));
        l1.add(new NestedInteger(3));
        nestedList.add(l1);
        nestedList.add(new NestedInteger(4));
        itr.add(new NestedIterator(nestedList));
        // DeadLock Case 2
        List<NestedInteger> nestedList1 = new ArrayList<NestedInteger>();
        NestedInteger l2 = new NestedInteger();
        nestedList1.add(new NestedInteger(3));
        l2.add(new NestedInteger(2));
        l2.add(new NestedInteger(3));
        l2.add(new NestedInteger(4));
        nestedList1.add(l2);
        nestedList1.add(new NestedInteger(4));
        nestedList1.add(l1);
        itr.add(new NestedIterator(nestedList1));
        // DeadLock Case 3
        List<NestedInteger> nestedList2 = new ArrayList<NestedInteger>();
        NestedInteger l3 = new NestedInteger();
        nestedList2.add(l1);
        nestedList2.add(new NestedInteger(3));
        l3.add(new NestedInteger(2));
        l3.add(new NestedInteger(3));
        l3.add(new NestedInteger(4));
        l3.add(new NestedInteger(5));
        nestedList2.add(l1);
        nestedList2.add(new NestedInteger(4));
        nestedList2.add(l3);
        itr.add(new NestedIterator(nestedList2));
        // DeadLock Case 4
        List<NestedInteger> nestedList3 = new ArrayList<NestedInteger>();
        nestedList3.add(new NestedInteger(1));
        NestedInteger l4 = new NestedInteger();
        l4.add(new NestedInteger(5));
        l4.add(new NestedInteger(6));
        NestedInteger l5 = new NestedInteger();
        l5.add(new NestedInteger(4));
        l5.add(l4);
        l5.add(new NestedInteger(7));
        NestedInteger l6 = new NestedInteger();
        l6.add(new NestedInteger(3));
        l6.add(l5);
        l6.add(new NestedInteger(8));
        nestedList3.add(l6);
        nestedList3.add(new NestedInteger(9));
        itr.add(new NestedIterator(nestedList3));
        // DeadLock Case 5
        List<NestedInteger> nestedList4 = new ArrayList<NestedInteger>();
        NestedInteger l7 = new NestedInteger();
        l7.add(new NestedInteger(2));
        l7.add(new NestedInteger(3));
        NestedInteger l8 = new NestedInteger();
        l8.add(new NestedInteger(2));
        l8.add(new NestedInteger(3));
        l8.add(l7);
        nestedList4.add(l8);
        itr.add(new NestedIterator(nestedList4));
        for (int i = 0; i < itr.size(); i++) {
            System.out.println((i + 1) + ".\tOriginal structure: " + inputs.get(i));
            System.out.println("\n\tOutput:\n");
            while (itr.get(i).hasNext()) {
                System.out.print("\titr.Next(): ");
                System.out.println(itr.get(i).next());
            }
        }
    }
}