package com.company;

// Klasse for alle objeketer -> Alle extender fra denne
/*
 * Litt av grunnen er at vi da kan iterate over alle objektene som exstender denne gjennom et object array
 */

import com.jogamp.opengl.GL2;

public class Object {

    boolean enable; // Om fysikk skal beregnes for dette objektet


    Object() {
        this.enable = true;
    }

    void draw(GL2 gl) {

    }

    void update() {


    }

}
