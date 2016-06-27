/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.List;

/**
 *
 * @author jonei
 * labels : ["January","February","March","April","May","June","July"],
                datasets : [
                                {
                                        label: "My First dataset",
                                        fillColor : "rgba(220,220,220,0.2)",
                                        strokeColor : "rgba(220,220,220,1)",
                                        pointColor : "rgba(220,220,220,1)",
                                        pointStrokeColor : "#fff",
                                        pointHighlightFill : "#fff",
                                        pointHighlightStroke : "rgba(220,220,220,1)",
                                        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
                                },
                                {
                                        label: "My Second dataset",
                                        fillColor : "rgba(48, 164, 255, 0.2)",
                                        strokeColor : "rgba(48, 164, 255, 1)",
                                        pointColor : "rgba(48, 164, 255, 1)",
                                        pointStrokeColor : "#fff",
                                        pointHighlightFill : "#fff",
                                        pointHighlightStroke : "rgba(48, 164, 255, 1)",
                                        data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
                                }
                            ]
 */
public class GraficoDatasets {
    private String label;
    private String fillColor;
    private String strokeColor;
    private String pointColor;
    private String pointStrokeColor;
    private String pointHighlightFill;
    private String pointHighlightStroke;
    private List<String> data;

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getPointColor() {
        return pointColor;
    }

    public void setPointColor(String pointColor) {
        this.pointColor = pointColor;
    }

    public String getPointStrokeColor() {
        return pointStrokeColor;
    }

    public void setPointStrokeColor(String pointStrokeColor) {
        this.pointStrokeColor = pointStrokeColor;
    }

    public String getPointHighlightFill() {
        return pointHighlightFill;
    }

    public void setPointHighlightFill(String pointHighlightFill) {
        this.pointHighlightFill = pointHighlightFill;
    }

    public String getPointHighlightStroke() {
        return pointHighlightStroke;
    }

    public void setPointHighlightStroke(String pointHighlightStroke) {
        this.pointHighlightStroke = pointHighlightStroke;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
    
}
