package com.wellD.patternRecognition.repository;

import com.wellD.patternRecognition.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Point.PointPK> {

    List<Point> findAll();

    Point save(Point point);

    void deleteAll();
}
