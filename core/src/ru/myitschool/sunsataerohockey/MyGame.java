package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyGame extends Game {
	public static final float WIDTH = 12.8f, HEIGHT = 7.2f;
	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	World world;
	Box2DDebugRenderer debugRenderer;
	Texture imgField;
	Texture imgBita0, imgBita1;
	Texture imgShayba;

	StaticBodyBox wallTop, wallDown;
	StaticBodyBox wallLeftT, wallLeftD, wallRightT, wallRightD;
	DynamicBodyBall shaiba;
	KinematicBodyBall bita0, bita1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		touch = new Vector3();
		Box2D.init();
		world = new World(new Vector2(0, 0), false);
		debugRenderer = new Box2DDebugRenderer();
		//img = new Texture("badlogic.jpg");
		wallDown = new StaticBodyBox(world, WIDTH/2, 1, WIDTH, 0.5f);
		wallTop = new StaticBodyBox(world, WIDTH/2, HEIGHT-1, WIDTH, 0.5f);
		wallLeftT = new StaticBodyBox(world, 1, HEIGHT/2, 0.5f, HEIGHT);
		wallRightT = new StaticBodyBox(world, WIDTH-1, HEIGHT/2, 0.5f, HEIGHT);

		shaiba = new DynamicBodyBall(world, WIDTH/2, HEIGHT/2, 0.5f);
		bita0 = new KinematicBodyBall(world, WIDTH/4, HEIGHT/2, 0.5f);
		bita1 = new KinematicBodyBall(world, WIDTH/4*3, HEIGHT/2, 0.5f);
	}

	@Override
	public void render () {

		if(Gdx.input.isTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			{
				bita0.oldX = bita0.getX();
				bita0.oldY = bita0.getY();
				bita0.body.setTransform(touch.x, touch.y, 0);
				if(bita0.contact(shaiba)) {
					shaiba.body.applyLinearImpulse(bita0.getImpulse(), shaiba.body.getPosition(), true);
				}
				System.out.println("contact: "+bita0.contact(shaiba));
			}
		}
		world.step(1/60f, 6, 2);
		ScreenUtils.clear(0, 0, 0, 1);
		debugRenderer.render(world, camera.combined);
		/*camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(int i=0; i<100; i++)
			batch.draw(img, ball.get(i).body.getPosition().x-0.3f,
					ball.get(i).body.getPosition().y-0.3f, 0.3f*2, 0.3f*2);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
