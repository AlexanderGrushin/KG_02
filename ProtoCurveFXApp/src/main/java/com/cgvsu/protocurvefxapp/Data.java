package com.cgvsu.protocurvefxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Data {
    public Data() {
    }

    public static int ControlPointsX[] = new int[10];
    public static int ControlPointsY[] = new int[10];
    public static int PointsX[] = new int[100];
    public static int PointsY[] = new int[100];
    public static int drawCurve = 0;
    public static int noCP = 0;
    public static int noTT = 0;

    public void insert(int x, int y) {
        ControlPointsX[noCP] = x;
        ControlPointsY[noCP] = y;
        noCP++;
        System.out.println("X[" + noCP + "]=" + x + " ,Y[" + noCP + "]=" + y);
    }

    public int getControlPointX(int index) {
        return ControlPointsX[index];
    }

    public int getControlPointY(int index) {
        return ControlPointsY[index];
    }

    public void insertCurvePoint(int x, int y) {
        PointsX[noTT] = x;
        PointsY[noTT] = y;
        System.out.println("[" + noTT + "]=" + x + " „Y[" + noTT + "]=" + y);
        noTT++;
    }

    public int getX(int i) {
        return PointsX[i];
    }

    public int getY(int i) {
        return PointsY[i];
    }

    public int getnoCP() {
        return noCP;
    }

    public void Reset() {
        drawCurve = 0;
        noCP = 0;
        noTT = 0;

    }
}