package com.company;

import com.jogamp.opengl.GL2;

import static com.company.Main.GRAVITY;

public class Point extends Object{

    private Vector position, velocity;
    private double mass;
    private boolean fixed;

    private Vector force;

    public Point(Vector position, Vector velocity, double mass, boolean fixed) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.fixed = fixed;
        this.force = new Vector(0, 0, 0);
    }

    public Point(Vector position, double mass, boolean fixed) {
        this.position = position;
        this.velocity = new Vector(0, 0, 0);
        this.mass = mass;
        this.fixed = fixed;
        this.force = new Vector(0, 0, 0);
    }

    void draw(GL2 gl) {
        gl.glPointSize(5);
        gl.glBegin(GL2.GL_POINTS);

        gl.glColor3d(0, 0, 1);
        gl.glVertex3d(position.x, position.y, position.z);
        gl.glEnd();
    }

    void update(Vector f, double k) {
        if (!fixed) {
            velocity = velocity.plus(f.multiply(k / mass)); // a = F / m // L = 0.99
        }
    }

    void update(Scene scene) {
        if (!fixed) {
            velocity = velocity.plus(force);
            velocity = velocity.plus(GRAVITY); // a = F / m
            velocity = velocity.multiply(1d / Math.pow(1d + velocity.length() / 15, 2));
            position = position.plus(velocity);
            for (Plane p : scene.getCollide()) {
                double d = p.getDistance(this);
                velocity = velocity.plus(new Vector(0, 0.9d / Math.pow(5 * d, 2), 0));
            }
        }
    }

    void addForce(Vector f) {
        force = force.plus(f);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
