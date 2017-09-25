package com.company;

import com.jogamp.opengl.GL2;


public class Spring extends Object{
    private Point p1, p2;
    private double k, length;

    Spring(Point p1, Point p2, double k) {
        this.p1 = p1;
        this.p2 = p2;
        this.k = k;
        this.length = p2.getPosition().minus(p1.getPosition()).length();
    }
    
    void draw(GL2 gl) {
        gl.glLineWidth(3);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(0, 1, 0);

        gl.glVertex3d(p1.getPosition().x, p1.getPosition().y, p1.getPosition().z);
        gl.glVertex3d(p2.getPosition().x, p2.getPosition().y, p2.getPosition().z);

        gl.glEnd();
    }

    void update(Scene scene) {
        // Denne skal adde en applied force istedet
        Vector v = p2.getPosition().minus(p1.getPosition());
        if (v.length() != 0) {
            v.multiply(1 / v.length());
        }
        double f = (k * extension()) / 100;
        p1.update(v, f); // F = kx
        p2.update(v.multiply(-1), f);
        if (extension() > length * 100d || extension() < length * -100d) {
            System.out.println("ERROR: " + extension());
        }
    }

    private double extension() {
        Vector v0 = p2.getPosition().minus(p1.getPosition());
        Vector v1 = (p2.getPosition().plus(p2.getVelocity())).minus(p1.getPosition().plus(p1.getVelocity()));
        return (v0.length() + v1.length()) / 2 - length;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public double getLength() {
        return length;
    }
}
