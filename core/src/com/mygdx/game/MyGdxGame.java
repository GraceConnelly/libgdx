package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import sun.jvm.hotspot.memory.Space;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion down, up, stand, right, left;

	float x, y, xv, yv;

	static final float MAX_VELOCITY = 100;
	static final float SPACE_VELOCITY = 800;

	static final int WIDTH = 18;
	static final int HEIGHT = 26;

	static final int DRAW_WIDTH = WIDTH*3;
	static final int DRAW_HEIGHT = HEIGHT*3;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		down = grid[6][0];
		up = grid[6][1];
		stand = grid[6][2];
		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255/255f, 160/255f, 122/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(stand, x, y , DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
		//time += Gdx.graphics.getDeltaTime();
		move();

		TextureRegion img;
		if (xv != 0) {
			img = xv > 0 ? right : left;
		}
		else if (yv != 0) {
			img = yv >0 ? up : down;
		}
		else {
			img = stand;
		}

		//Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	float decelerate(float velocity) {
		float deceleration = 0.95f; // the closer to 1, the slower the deceleration
		velocity *= deceleration;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

	void move() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;

		}
		y += yv * Gdx.graphics.getDeltaTime();
		x += xv * Gdx.graphics.getDeltaTime();

		yv = decelerate(yv);
		xv = decelerate(xv);

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			yv = SPACE_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			yv = SPACE_VELOCITY * -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			xv = SPACE_VELOCITY;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			xv = SPACE_VELOCITY * -1;

		}
	}

}
