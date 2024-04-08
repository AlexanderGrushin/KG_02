package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collection;

public class ProtoCurveController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    BezierCurve bc = new BezierCurve();

    ArrayList ptList = new ArrayList();


    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
            }
        });
    }

    private void handlePrimaryClick(GraphicsContext graphicsContext, MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());
        ptList.add(event.getX());
        ptList.add(event.getY());

        final int POINT_RADIUS = 3;
        graphicsContext.fillOval(
                clickPoint.getX() - POINT_RADIUS, clickPoint.getY() - POINT_RADIUS,
                2 * POINT_RADIUS, 2 * POINT_RADIUS);
        points.add(clickPoint);
        int POINTS_ON_CURVE = 10000;
        double[] p = new double[POINTS_ON_CURVE];
        double[] ptind = new double[ptList.size()];
        for (int i = 0; i < ptind.length; i++) {
            ptind[i] = (double)ptList.get(i);
        }

        if (points.size() > 1) {
            for (Point2D point : points) {
                graphicsContext.fillOval(
                        point.getX() - POINT_RADIUS, point.getY() - POINT_RADIUS,
                        2 * POINT_RADIUS, 2 * POINT_RADIUS);
            }
            bc.Bezier2D(ptind, (POINTS_ON_CURVE) / 2, p);

            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            for (int i = 1; i != POINTS_ON_CURVE-1; i += 2) {
                graphicsContext.strokeRect((int)p[i + 1], (int)p[i], 1, 1);
            }
        }


    }
}