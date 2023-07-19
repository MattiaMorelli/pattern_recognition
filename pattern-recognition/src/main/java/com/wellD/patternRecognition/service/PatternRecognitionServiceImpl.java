package com.wellD.patternRecognition.service;

import com.wellD.patternRecognition.domain.Line;
import com.wellD.patternRecognition.domain.Point;
import com.wellD.patternRecognition.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatternRecognitionServiceImpl implements PatternRecognitionService {

    @Autowired
    PointRepository pointRepository;

    @Override
    public Point savePoint(Point point) {
        return pointRepository.save(point);
    }

    @Override
    public List<Point> getAllPoints() {
        return pointRepository.findAll();
    }

    @Override
    public List<Line> findLinesWithNPoints(int N) {
        List<Line> result = new ArrayList<>();

        //Nested maps are used to group lines by slope and intercept
        Map<Double, Map<Double, Line>> slopeMap = new HashMap<>();

        // Retrieve all points from repository
        List<Point> points = pointRepository.findAll();

        // If no points retrieved OR the number of the points are less than the threshold N,
        // return empty array
        if(points.isEmpty() || points.size() < N) {
            return result;
        }

        // Iterate over all pairs of points to find the lines formed by each pair
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);

                // Calculate slope and intercept of the line formed by the pair p1 and p2
                double slope = calculateSlope(p1, p2);
                double intercept = calculateIntercept(slope, p1, p2);

                // Get or create the map of intercepts for the given slope
                slopeMap.putIfAbsent(slope, new HashMap<>());
                Map<Double, Line> interceptMap = slopeMap.get(slope);

                // Get or create the Line object for the given intercept
                interceptMap.putIfAbsent(intercept, new Line(slope, intercept));
                Line line = interceptMap.get(intercept);

                // Add the current points p1 and p2 to the line's points list
                line.getPoints().add(p1);
                line.getPoints().add(p2);
            }
        }

        // Iterate through all the lines stored in the slopeMap
        slopeMap.values().forEach(interceptMap ->
            interceptMap.values().forEach(line -> {
                // Check if the line contains N or more points, and if so, add it to the result list
                if(line.getPoints().size() >= N) {
                    result.add(line);
                }
        }));

        return result;
    }

    @Override
    public void deleteAllPoints() {
        pointRepository.deleteAll();
    }

    private double calculateSlope(Point p1, Point p2) {
        // If the two points have the same x-coordinate, the line is vertical and the slope is positive infinity
        if (p1.getX() == p2.getX()) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (double) (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        }
    }

    private double calculateIntercept(double slope, Point p1, Point p2) {
        // If the line is vertical (slope is infinite), the x-coordinate of the point is used as the intercept
        if (p1.getX() == p2.getX()) {
            return p1.getX();
        } else {
            return p1.getY() - slope * p1.getX();
        }
    }
}
