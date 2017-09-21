package com.company;

public class Vector {
    double x, y, z;

    Vector(double x, double y, double z) {
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

    Vector plus(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    Vector minus(Vector v) {
        return new Vector(x - v.x, y - v.y, v.z - z);
    }

    Vector multiply(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    Vector multiply(double t) {
        return new Vector(x * t, y * t, z * t);
    }

    double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    @Override
    public String toString() {
        return "Vector: (" + x + ", " + y + ", " + z + ")";
    }
}
