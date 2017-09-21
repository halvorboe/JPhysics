package com.company;


import java.awt.*;
import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import java.util.Random;


public class Main extends GLCanvas implements GLEventListener {

    private static String TITLE = "-";
    private static final int CANVAS_WIDTH = 1000;  // width of the drawable
    private static final int CANVAS_HEIGHT = 1000; // height of the drawable

    private GL2 gl;
    private GLU glu;

    Random random = new Random();

    Point p1 = new Point(new Vector(0, 4, 0), 1.0, true);
    Point p2 = new Point(new Vector(0, 3, 0), new Vector(0, 0, 0), 1.0, false);
    Point p3 = new Point(new Vector(0, 2, 0), new Vector(0, 0, 0), 1.0, false);
    Point p4 = new Point(new Vector(0, 1, 0), new Vector(0, 0, 0), 1.0, false);
    Spring s1 = new Spring(p1, p2, 0.01);
    Spring s2 = new Spring(p2, p3, 0.01);
    Spring s3 = new Spring(p3, p4, 0.01);

    Object[] objects = {p1, p2, p3, p4, s1, s2, s3}; // Liste  med alle objekter som er med i modellen

    private float angle = 0;

    static final Vector GRAVITY = new Vector(0, -0.0001, 0);

    public Main() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
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

        gl.glTranslatef(0.0f, 0.0f, -20f);
        gl.glRotatef(20, 1f, 0f, 0f);
        gl.glRotatef(angle, 0f, 1f, 0f);

        drawFloor();

        for (Object o : objects) {
            o.update();
        }

        for (Object o : objects) {
            o.draw(gl);
        }


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

        FPSAnimator animator = new FPSAnimator(canvas, 30, true);
        animator.start();
    }

    void drawFloor() {
        gl.glPointSize(1);
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(i, -3d, -10);
            gl.glVertex3d(i, -3d, 10);
            gl.glEnd();
        }
        for (int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-10, -3d, i);
            gl.glVertex3d(10, -3d, i);
            gl.glEnd();
        }
    }

    private double map(double v, double from_min, double to_min, double from_max, double to_max) {
        return (v - from_min) / (to_min - from_min) * (to_max - from_max) + from_max;
    }
}