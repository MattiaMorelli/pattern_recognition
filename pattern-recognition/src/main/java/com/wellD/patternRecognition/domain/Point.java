package com.wellD.patternRecognition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(Point.PointPK.class)
public class Point {

    @Id
    @NotNull
    private Integer x;
    @Id
    @NotNull
    private Integer y;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PointPK implements Serializable {
        private Integer x;
        private Integer y;
    }

}
