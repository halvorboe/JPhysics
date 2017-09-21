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
    private static final int CANVAS_WIDTH = 500;  // width of the drawable
    private static final int CANVAS_HEIGHT = 500; // height of the drawable

    private GL2 gl;
    private GLU glu;

    private float angle = 0;
    Sphere[] spheres;
    final int NUMBER_OF_SPHERES = 10;

    public Main() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glEnable(GL2.GL_DEPTH_TEST);           // enables depth testing
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glClearDepth(1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);


        Random random = new Random();

        spheres = new Sphere[NUMBER_OF_SPHERES];

        for(int i = 0; i < spheres.length; i++) {
            spheres[i] = new Sphere(random.nextDouble() * 4 - 2, random.nextDouble() * 4 - 2, random.nextDouble() * 4 - 2, random.nextDouble() * 1 - 0.5,random.nextDouble() * 1 - 0.5,random.nextDouble() * 1 - 0.5, 1d);
        }
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;

        //Set the view port (display area) to cover the entire window
        //gl.glViewport(0, 0, width/2, height/2);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }

    /**
     * Called by OpenGL for drawing
     */
    public void display(GLAutoDrawable drawable) {

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix



        // ----- Render a triangle -----
        // translated left and into the screen
        gl.glTranslatef(0.0f, 0.0f, -20f);
        gl.glRotatef(20, 1f, 0f, 0f);
        gl.glRotatef(angle, 0f, 1f, 0f);
        //gl.glRotatef(10, 1f, 0f, 0f);

        gl.glPointSize(1f);
         // draw using triangles
        drawGrid();

        for(Sphere s : spheres) {
            drawSphere(s);
        }




        angle += 0.12;

    }

    /**
     * Called before the OpenGL context is destroyed. Release resource such as buffers.
     */
    public void dispose(GLAutoDrawable drawable) { }

    /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
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

    void drawSphere(Sphere s) {

        final int RES = (int) Math.round(s.getR() * 30);
        for(int a = 5; a <= 5; a++) {
            gl.glLineWidth(25.2f - (float) Math.pow(a, 2));
        gl.glColor4d(s.getColor().x, s.getColor().y, s.getColor().z, 0.15 + (Math.pow(a, 2) * 3)/100);
        for(int lon = 0; lon < RES; lon++) {
            gl.glBegin(GL2.GL_LINE_STRIP);
            for (int lat = 0; lat < RES; lat++) {
                double lon_rad = map(lon, 0, RES, 0, 2 * Math.PI);
                double lat_rad = map(lat, 0, RES, 0, 2 * Math.PI);
                double x = s.getPos().x + s.getR() * Math.sin(lon_rad) * Math.cos(lat_rad);
                double y = s.getPos().y + s.getR() * Math.sin(lon_rad) * Math.sin(lat_rad);
                double z = s.getPos().z + s.getR() * Math.cos(lon_rad);
                //double lat_color = map(lat, 0, RES, 0.5d, 1d);
                //gl.glColor3d(lat_color, 0, 0);
                gl.glVertex3d(x, y, z);
            }
            gl.glEnd();
        }

        for(int lat = 0; lat < RES; lat++) {
            gl.glBegin(GL2.GL_LINE_STRIP);
            for (int lon = 0; lon < RES; lon++) {
                double lon_rad = map(lon, 0, RES, 0, 2 * Math.PI);
                double lat_rad = map(lat, 0, RES, 0, 2 * Math.PI);
                double x = s.getPos().x + s.getR() * Math.sin(lon_rad) * Math.cos(lat_rad);
                double y = s.getPos().y + s.getR() * Math.sin(lon_rad) * Math.sin(lat_rad);
                double z = s.getPos().z + s.getR() * Math.cos(lon_rad);
                //double lat_color = map(lat, 0, RES, 1d, 0.1d);
                //gl.glColor3d(lat_color, 0, 0);
                gl.glVertex3d(x, y, z);
            }
            gl.glEnd();
        }
    }
        s.update(spheres);
    }

    void drawGrid() {
        gl.glPointSize(1);
        for(int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(i , -3d, -10);
            gl.glVertex3d(i, -3d, 10);
            gl.glEnd();
        }
        for(int i = -10; i <= 10; i++) {
            gl.glBegin(GL2.GL_LINES);
            gl.glColor3d(1, 0, 0);
            gl.glVertex3d(-10 , -3d, i);
            gl.glVertex3d(10, -3d, i);
            gl.glEnd();
        }
    }

    // Helper functions


    private double map(double v, double from_min, double to_min, double from_max, double to_max) {
        return (v - from_min) / (to_min - from_min) * (to_max - from_max) + from_max;
    }
}