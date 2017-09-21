package com.company;

import com.jogamp.opengl.GL2;
import java.util.ArrayList;

public class Sphere extends Object {

    private Vector position;
    private double radius;
    private int resolution;
    private int pointCount;
    private Point[][] points;
    private ArrayList<Spring> springs;


    public Sphere(Vector position, double radius, int resolution) {
        this.position = position;
        this.radius = radius;
        this.resolution = resolution;
        this.pointCount = this.resolution*this.resolution;
    }

    void draw(GL2 gl){
        generate();
        for(Point[] pointArray : points){
            for(Point p : pointArray){
                p.draw(gl);
                p.update ();
            }
        }

        for(Spring s : springs){
            s.draw(gl);
            s.update();
        }
    }

    private void generate (){
        points = new Point[resolution+1][resolution+1];
        springs = new ArrayList<Spring> ();

        for(int lat = 0; lat <= resolution; lat++){
            double lat_angle = map(lat,0,resolution,0,Math.PI);
            for(int lon = 0; lon <= resolution; lon++){
                double lon_angle = map(lon,0,resolution,-Math.PI,Math.PI);
                double x = position.x + radius*Math.sin(lon_angle)*Math.cos(lat_angle);
                double y = position.y + radius*Math.sin(lon_angle)*Math.sin(lat_angle);
                double z = position.z + radius*Math.cos(lon_angle);
                Vector pos = new Vector(x,y,z);
                Point point = new Point(pos,1,false);
                points[lat][lon] = point;
            }
        }

        for(int i = 0; i < resolution; i++){
            for(int k = 0; k < resolution; k++){
                Point a = points[i][k];
                Point b = points[i][k + 1];
                Point c = points[i+1][k];
                Spring s1 = new Spring(a,b,1000);
                Spring s2 = new Spring(a,c,1000);
                //springs.add(s1);
                //springs.add(s2);
            }
        }
    }



    //Helper function
    private double map(double value,double from1, double to1, double from2, double to2){
        return (value - from1)/(to1 - from1)*(to2-from2)+from2;
    }
}
