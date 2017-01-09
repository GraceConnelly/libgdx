package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;

/**
 * Created by graceconnelly on 1/8/17.
 */
public class Person {
    TextureRegion img, down, up, stand, right;
    Animation walkLR;
    float x, y, xv, yv;
    int MAX_VELOCITY = 100;
    public enum Direction {UP, DOWN, LEFT, RIGHT}
    Direction direction;

    public Person (){}

    public Person(TextureRegion down, TextureRegion up, TextureRegion stand, TextureRegion right, Animation walkLR, float x, float y, int MAX_VELOCITY) {
        this.down = down;
        this.up = up;
        this.stand = stand;
        this.right = right;
        this.walkLR = walkLR;
        this.x = x;
        this.y = y;
        this.MAX_VELOCITY = MAX_VELOCITY;
    }

    public TextureRegion getImg() {
        return img;
    }

    public void setImg(TextureRegion img) {
        this.img = img;
    }

    public TextureRegion getDown() {
        return down;
    }

    public void setDown(TextureRegion down) {
        this.down = down;
    }

    public TextureRegion getUp() {
        return up;
    }

    public void setUp(TextureRegion up) {
        this.up = up;
    }

    public TextureRegion getStand() {
        return stand;
    }

    public void setStand(TextureRegion stand) {
        this.stand = stand;
    }

    public TextureRegion getRight() {
        return right;
    }

    public void setRight(TextureRegion right) {
        this.right = right;
    }

    public Animation getWalkLR() {
        return walkLR;
    }

    public void setWalkLR(Animation walkLR) {
        this.walkLR = walkLR;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getXv() {
        return xv;
    }

    public void setXv(float xv) {
        this.xv = xv;
    }

    public float getYv() {
        return yv;
    }

    public void setYv(float yv) {
        this.yv = yv;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getMAX_VELOCITY() {
        return MAX_VELOCITY;
    }

    public void setMAX_VELOCITY(int MAX_VELOCITY) {
        this.MAX_VELOCITY = MAX_VELOCITY;
    }

    public static void move(List<Person> people) {
        for (Person p : people) {
            int MAX_VELOCITY = p.getMAX_VELOCITY();
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                p.setYv(MAX_VELOCITY);
                p.setDirection(Direction.UP);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                p.setYv(MAX_VELOCITY * -1);
                p.setDirection(Direction.DOWN);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                p.setXv(MAX_VELOCITY);
                p.setDirection(Direction.RIGHT);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                p.setXv(MAX_VELOCITY * -1);
                p.setDirection(Direction.LEFT);
            }
            moveCharacter(p);
            p.setYv(decelerate(p.getYv()));
            p.setXv(decelerate(p.getXv()));
        }
    }

    static float decelerate(float velocity) {
        float deceleration = 0.94f; // the closer to 1, the slower the deceleration
        velocity *= deceleration;
        if (Math.abs(velocity) < 1) {
            velocity = 0;
        }
        return velocity;
    }

    static void moveCharacter(Person p){
        float windowY = (float)Gdx.graphics.getHeight();
        float windowX = (float)Gdx.graphics.getWidth();
        float acclFctr = (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ? 3.5f : 1.0f;

        if (p.getY() < 0) {
            p.setY(windowY /*- imgY*/);
        } else if ((p.getY() /*+ imgY*/) > windowY) {
            p.setY(0);
        } else {
            p.setY( p.getY() + (p.getYv()* acclFctr * Gdx.graphics.getDeltaTime()));
        }
        if (p.getX() < 0) {
            p.setX(windowX /*- imgX*/);
        } else if ((p.getX() /*+ imgX*/) > windowX) {
            p.setX(0);
        } else {
            p.setX(p.getX() + (p.getXv() * acclFctr * Gdx.graphics.getDeltaTime()));
        }
    }
    static void defineImgDirection(List<Person> people){

        for (Person p :people) {
            if(p.getDirection() != null) {
                switch (p.getDirection()) {
                    case DOWN:
                        p.setImg(p.getDown());
                        break;
                    case UP:
                        p.setImg(p.getUp());
                        break;
                    case LEFT:
                    case RIGHT:
                        //p.setImg(p.getWalkLR());
                        p.setImg(p.getRight());
                        break;
                }
            }else p.setImg(p.getStand());
        }
    }
}


