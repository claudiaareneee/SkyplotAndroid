package com.example.claudia.skyplotchart;

import android.content.Context;

public class DataPoint {
    private float azimuth, elevation;
    private DataPointView dataPointView;
    String id;

    public DataPoint(String id, float azimuth, float elevation){
        this.id = id;
        this.azimuth = azimuth;
        this.elevation = elevation;
    }

    public float getAzimuth(){
        return azimuth;
    }

    public float getElevation(){
        return elevation;
    }

}
