package com.company;

import java.util.Random;

import static com.company.Main.GRAVITY;

public class Sphere {

    private double r;
    private Vector pos, vel, color;

    final double gravity = 0.01;

    public Sphere(double x, double y, double z, double x_vel, double y_vel, double z_vel, double r) {
        this.r = r;
        this.pos = new Vector(x, y, z);
        this.vel = new Vector(x_vel, y_vel, z_vel);

        Random random = new Random();
        this.color = new Vector(random.nextDouble() / 2 + 0.5, random.nextDouble() / 2 + 0.5, random.nextDouble() / 2 + 0.5);

    }

    public Vector getColor() {
        return color;
    }


    public void update(Sphere[] spheres) {

            pos  = pos.plus(vel);

            if (pos.x < -10 + r || pos.x > 10 - r) {
                vel.x = -vel.x;
                pos.x = pos.x > 0 ? 10 - r : -10 + r;
            }
            else if (pos.y < -3 + r || pos.y > 10 - r) {
                vel.y = -vel.y * 0.98;
                pos.y = pos.y > 0 ? 10 - r : -3 + r;
            }
            else if (pos.z < -10 + r || pos.z > 10 - r) {
                vel.z = -vel.z;
                pos.z = pos.z > 0 ? 10 -r  : -10 + r;
            }
            else {
                for (Sphere s : spheres) {
                    double d = Math.sqrt(Math.pow(s.pos.x - pos.x, 2) + Math.pow(s.pos.y - pos.y, 2) + Math.pow(s.pos.z - pos.z, 2));
                    if (d < s.r + r && d != 0) {
                        Random random = new Random();
                        color.x = random.nextDouble() / 2 + 0.5;
                        color.y = random.nextDouble() / 2 + 0.5;
                        color.z = random.nextDouble() / 2 + 0.5;
                    }
                }

                // Gravity
                vel = vel.plus(GRAVITY);

                // Resistance
                vel = vel.multiply(0.99);
            }


    }

    public Vector getPos() {
        return pos;
    }

    public double getR() {
        return r;
    }

}
