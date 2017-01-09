package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by graceconnelly on 1/8/17.
 */
public class Zombie extends Person {
    TextureRegion img, down, up, stand, right;
    Animation walkLR;
    float x, y, xv, yv;
    int MAX_VELOCITY = 300;
    public enum Direction {UP, DOWN, LEFT, RIGHT}
    Person.Direction direction;

    public Zombie (){}

    public Zombie(TextureRegion down, TextureRegion up, TextureRegion stand, TextureRegion right, Animation walkLR, float x, float y, int MAX_VELOCITY) {
        this.down = down;
        this.up = up;
        this.stand = stand;
        this.right = right;
        this.walkLR = walkLR;
        this.x = x;
        this.y = y;
        this.MAX_VELOCITY = MAX_VELOCITY;
    }
}
