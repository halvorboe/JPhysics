package com.company;

import com.jogamp.opengl.GL2;

public class Plane extends Object{

    final double H = 5.0;

    Plane() {
        this.enable = true;
    }

    void draw(GL2 gl) {
        gl.glLineWidth(1);
        gl.glPointSize(1);
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(i, -H, -10);
            gl.glVertex3d(i, -H, 10);
            gl.glEnd();
        }
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-10, -H, i);
            gl.glVertex3d(10, -H, i);
            gl.glEnd();
        }
    }

    public double getDistance(Point point) {
        return point.getPosition().y + H;
    }

}
