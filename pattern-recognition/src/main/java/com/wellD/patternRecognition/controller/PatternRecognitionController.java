package com.wellD.patternRecognition.controller;

import com.wellD.patternRecognition.domain.Line;
import com.wellD.patternRecognition.domain.Point;
import com.wellD.patternRecognition.service.PatternRecognitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatternRecognitionController {

    @Autowired
    PatternRecognitionService patternRecognitionService;

    @PostMapping("/point")
    public ResponseEntity savePoint(@RequestBody @Valid Point point, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        Point newPoint = patternRecognitionService.savePoint(point);
        return new ResponseEntity<>(newPoint, HttpStatus.CREATED);
    }

    @GetMapping("/space")
    public ResponseEntity<List<Point>> getAllPoints() {
        List<Point> points = patternRecognitionService.getAllPoints();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/lines/{n}")
    public ResponseEntity<List<Line>> findLinesWithNPoints(@PathVariable int n) {
        List<Line> linesWithNPoints = patternRecognitionService.findLinesWithNPoints(n);
        return new ResponseEntity<>(linesWithNPoints, HttpStatus.OK);
    }

    @DeleteMapping("/space")
    public ResponseEntity deleteAllPoints() {
        patternRecognitionService.deleteAllPoints();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
