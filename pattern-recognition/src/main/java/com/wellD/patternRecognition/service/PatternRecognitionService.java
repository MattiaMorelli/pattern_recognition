package com.wellD.patternRecognition.service;

import com.wellD.patternRecognition.domain.Line;
import com.wellD.patternRecognition.domain.Point;

import java.util.List;

public interface PatternRecognitionService {

    Point savePoint(Point point);

    List<Point> getAllPoints();

    List<Line> findLinesWithNPoints(int N);

    void deleteAllPoints();

}