package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.CircleShape;

public class Tools {
    public static Texture[] loadTextures(int frames, String path) {
        Texture[] textures = new Texture[frames];

        for (int i = 0; i < frames; i++) {
            textures[i] = new Texture(path + i +".png");
        }

        return textures;
    }
}
