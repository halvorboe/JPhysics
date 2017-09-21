package com.company;


import java.util.ArrayList;

public class Scene {

    ArrayList<Object> objects = new ArrayList<Object>();

    Scene() {

    }

    void addObject(Object o) {
        objects.add(o);
    }

    void removeObject(Object o) {
        objects.remove(o);
    }

}
