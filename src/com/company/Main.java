package com.company;


import java.awt.*;
import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.util.Arrays;

import java.util.Random;


public class Main extends GLCanvas implements GLEventListener {

    private static String TITLE = "-";
    private static final int CANVAS_WIDTH = 1000;  // width of the drawable
    private static final int CANVAS_HEIGHT = 1000; // height of the drawable

    private GL2 gl;
    private GLU glu;

    Random random = new Random();

    Scene scene = new Scene();

    Point p1 = new Point(new Vector(0, 4, 0), 1.0, true);
    Point p2 = new Point(new Vector(0, 3, 0), new Vector(-10, 0, 0), 1.0, false);
    Point p3 = new Point(new Vector(0, 2, 0), new Vector(0, 0, 0), 1.0, false);
    Point p4 = new Point(new Vector(0, 1, 0), new Vector(0, 0, 0), 1.0, false);
    Spring s1 = new Spring(p1, p2, 0.05);
    Spring s2 = new Spring(p2, p3, 0.05);
    Spring s3 = new Spring(p3, p4, 0.05);
    Floor f = new Floor();

    //scene.addObject();
    // Liste  med alle objekter som er med i modellen

    private float angle = 0;

    static final Vector GRAVITY = new Vector(0, -0.005, 0);

    public Main() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.1f, 0.1f, 0.1f, 0.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        scene.addObject(p1);
        scene.addObject(p2);
        scene.addObject(p3);
        scene.addObject(p4);
        scene.addObject(s1);
        scene.addObject(s2);
        scene.addObject(s3);
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        if (height == 0) height = 1;
        float aspect = (float) width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    /**
     * Called by OpenGL for drawing
     */
    public void display(GLAutoDrawable drawable) {

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -15f);
        gl.glRotatef(20, 1f, 0f, 0f);
        gl.glRotatef(angle, 0f, 1f, 0f);

        scene.iterate(gl);
        angle += 0.12;

    }

    /**
     * Called before the OpenGL context is destroyed. Release resource such as buffers.
     */
    public void dispose(GLAutoDrawable drawable) {
    }

    /**
     * The entry main() method to setup the top-level JFrame with our OpenGL canvas inside
     */
    public static void main(String[] args) {
        GLCanvas canvas = new Main();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();
    }

    private double map(double v, double from_min, double to_min, double from_max, double to_max) {
        return (v - from_min) / (to_min - from_min) * (to_max - from_max) + from_max;
    }
}