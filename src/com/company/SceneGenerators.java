package com.company;

public class SceneGenerators {
    public static Scene cloth(Scene scene) {

        Point[][] points = new Point[10][10]; //sd

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                points[i][j] = new Point(new Vector(4d - j, 9d - i, 0), 1.0, i == 0 && (j == 0 || j == 9));
                scene.addObject(points[i][j]);
            }
        }

        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i - 1][j], 0.05));
                }
                if (j - 1 > 0) {
                    scene.addObject(new Spring(points[i][j], points[i][j - 1], 0.05));
                }
                if (i + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i + 1][j], 0.05));
                }
                if (j + 1 < 10) {
                    scene.addObject(new Spring(points[i][j], points[i][j + 1], 0.05));
                }
            }
        }

        Plane f = new Plane();
        scene.addObject(f);

        for (Object o : scene.objects) {
            o.addForce(new Vector(0.01, 0, -0.0));
        }
        System.out.println(scene);

        return scene;

    }
}
