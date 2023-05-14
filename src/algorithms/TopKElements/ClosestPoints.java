package algorithms.TopKElements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class ClosestPoints {

    public static List<Point> kClosest(Point[] points, int k) {
        PriorityQueue<double[]> minHeapForSqrt = new PriorityQueue<>((a, b) -> Double.compare(a[0], b[0]));
        for (Point point : points) {
            minHeapForSqrt.offer(new double[]{Math.sqrt(point.x * point.x + point.y * point.y), point.x, point.y});
        }
        List<Point> ans = new ArrayList<>();
        int count = 0;
        while (!minHeapForSqrt.isEmpty() && count < k) {
            double[] maxSqrt = minHeapForSqrt.poll();
            ans.add(new Point((int) maxSqrt[1], (int) maxSqrt[2]));
            count++;
        }
        return ans;
    }

    //    public static void main(String[] args) {
//        Point[] pointsOne = new Point[]{
//                new Point(1, 3), new Point(3, 4), new Point(2, -1)
//        };
//        System.out.println(kClosest(pointsOne, 9));
//    }
    public static void main(String[] args) {
        Point[] pointsOne = new Point[]{
                new Point(1, 3), new Point(3, 4), new Point(2, -1)
        };
        Point[] pointsTwo = new Point[]{
                new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2)
        };
        Point[] pointsThree = new Point[]{
                new Point(1, 3), new Point(5, 3), new Point(3, -2), new Point(-2, 2)
        };
        Point[] pointsFour = new Point[]{
                new Point(2, -1), new Point(-2, 2), new Point(1, 3), new Point(2, 4)
        };
        Point[] pointsFive = new Point[]{
                new Point(1, 3), new Point(2, 4), new Point(2, -1), new Point(-2, 2), new Point(5, 3), new Point(3, -2), new Point(5, 3), new Point(3, -2)
        };
        Point[][] points = {pointsOne, pointsTwo, pointsThree, pointsFour, pointsFive};
        int[] kList = {2, 3, 1, 4, 5};
        for (int i = 0; i < points.length; i++) {

            System.out.print((i + 1) + ".\tSet of points: ");
            for (Point p : points[i]) {
                System.out.print("[" + p.x + " , " + p.y + "] ");
            }
            System.out.println("\n\tK: " + kList[i]);
            List<Point> result = ClosestPoints.kClosest(points[i], kList[i]);
            System.out.print("\tHere are the k = " + kList[i] + " points closest to the origin(0, 0): ");
            for (Point p : result) {
                System.out.print("[" + p.x + " , " + p.y + "] ");
            }
            System.out.println("\n");
        }
    }
}