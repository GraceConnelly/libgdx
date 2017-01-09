package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by graceconnelly on 1/8/17.
 */
public class Zombie extends Person {
    public Zombie(TextureRegion down, TextureRegion up, TextureRegion stand, TextureRegion right, Animation walkLR, float x, float y, int MAX_VELOCITY) {
        super(down, up, stand, right, walkLR, x, y, MAX_VELOCITY);
    }
    //TODO: move towards a randomly selected location on the screen. once he gets there pick a new location
}
