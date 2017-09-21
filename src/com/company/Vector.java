package com.company;

public class Vector {
    double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector plus(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public Vector minus(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public Vector multiply(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public Vector multiply(double t) {
        return new Vector(x * t, y * t, z * t);
    }

}
