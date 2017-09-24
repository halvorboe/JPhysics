package com.company;


import com.jogamp.opengl.GL2;

import java.io.Serializable;
import java.util.ArrayList;

public class Scene implements Serializable {

    ArrayList<Object> objects;
    ArrayList<Plane> collide;

    Scene() {
        objects = new ArrayList<Object>();
        collide = new ArrayList<Plane>();
    }

    public void addObject(Plane p) {
        objects.add(p);
        collide.add(p);
    }

    public void addObject(Object o) {
        objects.add(o);
    }

    public void removeObject(Object o) {
        objects.remove(o);
    }

    public void iterate(GL2 gl) {
        for (Object o : objects) {
            o.update(this);
        }
        for (Object o : objects) {
            o.draw(gl);
            //o.addForce(new Vector(0, 0, 0));
        }

    }

    public String toString() {
        String s = "Scene: \n";
        for(Object o : objects) {
            s += o + "\n";
        }
        return s;
    }

    public ArrayList<Plane> getCollide() {
        return collide;
    }
}
