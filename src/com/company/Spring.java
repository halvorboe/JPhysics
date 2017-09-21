package com.company;

import com.jogamp.opengl.GL2;

public class Spring extends Object {
    private Point p1, p2;
    private double k, length;

    Spring(Point p1, Point p2, double k) {
        this.p1 = p1;
        this.p2 = p2;
        this.k = k;
        this.length = p2.getPos().minus(p1.getPos()).length();

    }
    
    void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0, 1, 0);

        gl.glVertex3d(p1.getPos().x, p1.getPos().y, p1.getPos().z);
        gl.glVertex3d(p2.getPos().x, p2.getPos().y, p2.getPos().z);

        gl.glEnd();
    }

    void update() {
        // Denne skal adde en applied force istedet
        p1.update(p2.getPos().minus(p1.getPos()), k * extension()); // F = kx
        p2.update(p1.getPos().minus(p2.getPos()), k * extension());
    }

    private double extension() {
        return p2.getPos().minus(p1.getPos()).length() - length;
    }
}
