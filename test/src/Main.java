import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(1, 2));
        points.add(new Point(2, 4));
        points.add(new Point(3, 6));

        int threshold = 3;

        List<Line> lines = findLines(points, threshold);

        for (Line line : lines) {
            System.out.println("Line with " + threshold + " or more points:");
            System.out.println("Point 1: (" + line.p1.x + ", " + line.p1.y + ")");
            System.out.println("Point 2: (" + line.p2.x + ", " + line.p2.y + ")");
            System.out.println();
        }
    }

    static List<Line> findLines(List<Point> points, int threshold) {
        List<Line> result = new ArrayList<>();
        Map<Double, List<Point>> slopeMap = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double slope = calculateSlope(p1, p2);

                slopeMap.putIfAbsent(slope, new ArrayList<>());
                slopeMap.get(slope).add(p1);
                slopeMap.get(slope).add(p2);
            }
        }

        for (Map.Entry<Double, List<Point>> entry : slopeMap.entrySet()) {
            List<Point> linePoints = entry.getValue();
            if (linePoints.size() >= threshold) {
                result.add(new Line(linePoints.get(0), linePoints.get(1)));
            }
        }

        return result;
    }

    private static double calculateSlope(Point p1, Point p2) {
        if (p1.x == p2.x) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (p2.y - p1.y) / (p2.x - p1.x);
    }


}

