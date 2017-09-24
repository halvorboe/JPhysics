package com.company;

import com.jogamp.opengl.GL2;

public class Plane extends Object{

    Plane() {
        this.enable = true;
    }

    void draw(GL2 gl) {
        gl.glLineWidth(1);
        gl.glPointSize(1);
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(i, -10d, -10);
            gl.glVertex3d(i, -10d, 10);
            gl.glEnd();
        }
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-10, -10d, i);
            gl.glVertex3d(10, -10d, i);
            gl.glEnd();
        }
    }

    public double getDistance(Point point) {
        return point.getPosition().y + 10d;
    }

}
