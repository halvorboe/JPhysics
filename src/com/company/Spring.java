package com.company;

import com.jogamp.opengl.GL2;

public class Spring {
    private Point from, to;
    private double k, length;

    Spring(Point from, Point to, double k) {
        this.from = from;
        this.to = to;
        this.k = k;
        this.length = to.getPos().minus(from.getPos()).length();

    }

    void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0, 1, 0);

        gl.glVertex3d(from.getPos().x, from.getPos().y, from.getPos().z);
        gl.glVertex3d(to.getPos().x, to.getPos().y, to.getPos().z);

        gl.glEnd();

        from.draw(gl);
        to.draw(gl);

    }

    void update() {
        from.update(to.getPos().minus(from.getPos()), k * extension()); // F = kx
        to.update(from.getPos().minus(to.getPos()), k * extension());
    }

    double extension() {
        return to.getPos().minus(from.getPos()).length() - length;
    }
}
