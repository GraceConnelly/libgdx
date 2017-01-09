package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import sun.jvm.hotspot.utilities.HeapGXLWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.floorDiv;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
//	TextureRegion img, down, up, stand, right;
//	Animation walkLR;
//	public enum Direction {UP, DOWN, LEFT, RIGHT}
//	Direction direction;
//	float x, y, xv, yv;
	List<Person> people = new ArrayList<Person>();
	float time;
	static final int WIDTH = 18;
	static final int HEIGHT = 26;

	static final int DRAW_WIDTH = WIDTH * 3;
	static final int DRAW_HEIGHT = HEIGHT * 3;
//	d
//	static int randX (){
//		Math.random()
//	}

	@Override
	public void create() {
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false,w,h);
//		camera.update();
//		tiledMap = new TmxMapLoader().load("MyCrappyMap.tmx");
//		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
		//Make Player
		people.add(new Player(grid[6][0], grid[6][1], grid[6][2], grid[6][3], new Animation(0.2f, grid[6][3], grid[6][2]), 0 , 0, 100));
		people.add(new Zombie(grid[6][4],grid[6][5], grid[6][6], grid[6][7], new Animation(0.2f, grid[6][7],grid[6][6]), 80, 80, 100));
	}

	@Override
	public void render() {
		time += Gdx.graphics.getDeltaTime();
		Person.move(people);
		Person.defineImgDirection(people);

		Gdx.gl.glClearColor(255 / 255f, 160 / 255f, 122 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Person p : people) {
			if (p.direction == Person.Direction.LEFT) {
				batch.draw(p.getImg(), p.getX() + DRAW_WIDTH, p.getY(), DRAW_WIDTH * -1, DRAW_HEIGHT);
			} else {
				batch.draw(p.getImg(), p.getX(), p.getY(), DRAW_WIDTH, DRAW_HEIGHT);
			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
