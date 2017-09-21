package com.company;

import com.jogamp.opengl.GL2;

import static com.company.Main.GRAVITY;

public class Point extends Object {

    private Vector pos, vel;
    private double mass;
    private boolean fixed;

    public Point(Vector pos, Vector vel, double mass, boolean fixed) {
        this.pos = pos;
        this.vel = vel;
        this.mass = mass;
        this.fixed = fixed;
    }

    public Point(Vector pos, double mass, boolean fixed) {
        this.pos = pos;
        this.vel = new Vector(0, 0, 0);
        this.mass = mass;
        this.fixed = fixed;
    }

    void draw(GL2 gl) {
        gl.glPointSize(5);
        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3d(0, 0, 1);
        gl.glVertex3d(pos.x, pos.y, pos.z);
        gl.glEnd();
    }

    void update(Vector f, double k) {
        if (!fixed) {
            vel = vel.plus(f.multiply(k / mass)).plus(GRAVITY); // a = F / m
            pos = pos.plus(vel);
            vel = vel.multiply(0.99); // L = 0.99
        }
    }

    public Vector getPos() {
        return pos;
    }

    public Vector getVel() {
        return vel;
    }

    public double getMass() {
        return mass;
    }
}
