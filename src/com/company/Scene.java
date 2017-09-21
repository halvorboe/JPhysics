package com.company;


import com.jogamp.opengl.GL2;

import java.util.ArrayList;

public class Scene {

    ArrayList<Object> objects;

    Scene() {
        objects = new ArrayList<Object>();
    }

    public void addObject(Object o) {
        objects.add(o);
    }

    public void removeObject(Object o) {
        objects.remove(o);
    }

    public void iterate(GL2 gl) {
        for (Object o : objects) {
            o.update();
        }
        for (Object o : objects) {
            o.draw(gl);
        }
    }

}
