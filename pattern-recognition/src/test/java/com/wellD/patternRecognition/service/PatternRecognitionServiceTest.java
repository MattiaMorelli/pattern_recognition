package com.wellD.patternRecognition.service;

import com.wellD.patternRecognition.domain.Line;
import com.wellD.patternRecognition.domain.Point;
import com.wellD.patternRecognition.repository.PointRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PatternRecognitionServiceTest {
    @Autowired
    private PatternRecognitionService patternRecognitionService;

    @Autowired
    private PointRepository pointRepository;

    @BeforeEach
    public void setUp() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 3);
        Point point3 = new Point(3, 4);
        Point point4 = new Point(3, 5);
        Point point5 = new Point(4, 4);
        Point point6 = new Point(5, 6);
        pointRepository.save(point1);
        pointRepository.save(point2);
        pointRepository.save(point3);
        pointRepository.save(point4);
        pointRepository.save(point5);
        pointRepository.save(point6);
    }

    @AfterEach
    public void cleanUp() {
        pointRepository.deleteAll();
    }

    @Test
    public void testFindLinesWithNPoints() {

        // Call the method under test
        int N = 3;
        List<Line> result = patternRecognitionService.findLinesWithNPoints(N);

        // Assert the result
        assertEquals(2, result.size()); // Expected number of lines with N or more points

        // Assert the properties of the lines
        Line line1 = result.get(0);
        assertEquals(1.0, line1.getSlope());
        assertEquals(1.0, line1.getIntercept());
        assertEquals(3, line1.getPoints().size());

        Line line2 = result.get(1);
        assertEquals(Double.POSITIVE_INFINITY, line2.getSlope());
        assertEquals(3.0, line2.getIntercept());
        assertEquals(3, line2.getPoints().size());
    }

}
