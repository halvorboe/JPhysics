package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SceneGenerators {
    public static Scene cloth(Scene scene) {

        Point[][] points = new Point[10][10]; //sd

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                points[i][j] = new Point(new Vector(4d - j, 9d - i, i), 1.0, i == 0 && (j == 0 || j == 9));
                scene.addObject(points[i][j]);
            }
        }

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i + 1][j], 10));
                }
                if (j + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i][j + 1], 10));
                }
            }
        }

        for (Object o : scene.objects) {
            o.addForce(new Vector(0.0, 0, -0.001));
        }
        System.out.println(scene);

        return scene;

    }

    public static Scene sphere(Scene scene){
        /*Vector position = new Vector(0,0,0);
        double radius = 1;
        int resolution = 13;
        Point[][] points = new Point[resolution+1][resolution+1];

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
                //point.addForce(new Vector(
                //        radius*Math.sin(lon_angle)*Math.cos(lat_angle),
                //        radius*Math.sin(lon_angle)*Math.sin(lat_angle),
                //        radius*Math.cos(lon_angle)).multiply(0.001));
                scene.addObject(point);
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length; j++) {
                if (i - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i - 1][j], 0.005));
                }
                if (j - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i][j - 1], 0.005));
                }
                if (i + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i + 1][j], 0.005));
                }
                if (j + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i][j + 1], 0.005));
                }
            }
        }*/
        int samples = 100;
        Random rand = new Random();
        double rnd = rand.nextDouble()*samples;

        Point[] points = new Point[samples];
        double offset = 4.0/samples;
        double increment = Math.PI*(3.0-Math.sqrt(5.0));

        for(int i = 0; i < samples; i++){
            double y = ((i*offset) - 1) + (offset/2.0);
            double r = Math.sqrt(1 - Math.pow(y,2));

            double angle = ((i + rnd)%samples)*increment;

            double x = Math.cos(angle)*r;
            double z = Math.sin(angle)*r;
            Vector pos = new Vector(x,y,z);
            points[i] = new Point(pos, 1, false);
            scene.addObject(points[i]);
        }

        final int NUMBER_OF_SPRINGS = (int) Math.round(samples * 2.2);

        ArrayList<Spring> springs = new ArrayList<Spring>();

        //Connects the points with springs
        for (int n = 0; n < NUMBER_OF_SPRINGS; n++) {
            Spring spring = new Spring(new Point(new Vector(0, 0, 0), 10, false), new Point(new Vector(100000, 100000, 1000000), 100, false), 1);
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points.length; j++) {
                    Vector p1 = points[i].getPosition();
                    Vector p2 = points[j].getPosition();
                    if (p1.minus(p2).length() < spring.getLength()) {
                        boolean found = false;
                        for (Spring s : springs) {
                            if ((p1 == s.getP1().getPosition() && p2 == s.getP2().getPosition()) || (p2 == s.getP1().getPosition() && p1 == s.getP2().getPosition())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            spring = new Spring(points[i], points[j], 100);
                        }
                    }

                }
            }
            springs.add(spring);
            scene.addObject(spring);

        }

        for(Point p : points) {
            p.addForce(p.getPosition().multiply(0.02));
        }

        scene.addObject(new Plane());

        return scene;
    }
    //Helper function
    private static double map(double value,double from1, double to1, double from2, double to2){
        return (value - from1)/(to1 - from1)*(to2-from2)+from2;
    }
}
