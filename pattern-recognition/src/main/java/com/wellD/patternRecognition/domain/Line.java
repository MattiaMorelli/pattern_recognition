package com.wellD.patternRecognition.domain;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Line {

    private double slope;
    private double intercept;
    private Set<Point> points;

    public Line(double slope, double intercept) {
        this.slope = slope;
        this.intercept = intercept;
        this.points = new HashSet<>();
    }

}
