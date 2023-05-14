package dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzn
 * @date 2022/12/01 21:48
 * @Description
 */
public class SongRecommendedSystem {


    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        //交集[1, 2]
        List<Integer> ans = list1.stream().filter(list2::contains).collect(Collectors.toList());
        //差集[3, 4]
        List<Integer> ans1 = list1.stream().filter(num -> !list2.contains(num)).collect(Collectors.toList());
        System.out.println(ans);
        System.out.println(ans1);
    }
}
