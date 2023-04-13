package com.javalab.lab6.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.shape.Line;

import java.util.Objects;

public class SerializedLine {
    private Double startX;
    private Double startY;
    private Double endX;
    private Double endY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializedLine that = (SerializedLine) o;
        return Objects.equals(startX, that.startX) && Objects.equals(startY, that.startY) && Objects.equals(endX, that.endX) && Objects.equals(endY, that.endY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, startY, endX, endY);
    }

    public SerializedLine() {

    }

    public SerializedLine(Double startX, Double startY, Double endX, Double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public static SerializedLine fromLine(Line line) {
        return new SerializedLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
    }

    public Double getStartX() {
        return startX;
    }

    public void setStartX(Double startX) {
        this.startX = startX;
    }

    public Double getStartY() {
        return startY;
    }

    public void setStartY(Double startY) {
        this.startY = startY;
    }

    public Double getEndX() {
        return endX;
    }

    public void setEndX(Double endX) {
        this.endX = endX;
    }

    public Double getEndY() {
        return endY;
    }

    public void setEndY(Double endY) {
        this.endY = endY;
    }

    public Line toLine() {
        return new Line(startX, startY, endX, endY);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(this));
        return gson.toJson(this);
    }
}