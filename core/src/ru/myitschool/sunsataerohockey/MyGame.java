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
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        touch = new Vector3();
        Box2D.init();
        world = new World(new Vector2(0, 0), false);
        debugRenderer = new Box2DDebugRenderer();

        imgField = new Texture("field.png");
        imgBita0 = new Texture("BitaC.png");
        imgBita1 = new Texture("BitaG.png");
        imgShayba = new Texture("BitaY.png");

        wallTop = new StaticBodyBox(world, WIDTH / 2, HEIGHT, WIDTH, 0.4f);
		wallDown = new StaticBodyBox(world, WIDTH / 2, 0, WIDTH, 0.4f);

        wallLeftT = new StaticBodyBox(world, 0, HEIGHT, 0.4f, HEIGHT/6*4);
        wallLeftD = new StaticBodyBox(world, 0, 0, 0.4f, HEIGHT/6*4);

        wallRightT = new StaticBodyBox(world, WIDTH, HEIGHT, 0.4f, HEIGHT/6*4);
        wallRightD = new StaticBodyBox(world, WIDTH, 0, 0.4f, HEIGHT/6*4);

        shaiba = new DynamicBodyBall(world, WIDTH / 2, HEIGHT / 2, 0.5f);
        bita0 = new KinematicBodyBall(world, WIDTH / 4, HEIGHT / 2, 0.5f);
        bita1 = new KinematicBodyBall(world, WIDTH / 4 * 3, HEIGHT / 2, 0.5f);
    }

    @Override
    public void render() {
		// касания
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
        }

        for (int i = 0; i < 2; i++) {
            if (Gdx.input.isTouched(i)) {
                touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touch);
                if (touch.x < WIDTH / 2) {
                    bita0.setOldXY();
                    bita0.body.setTransform(touch.x, touch.y, 0);
                    if (bita0.contact(shaiba)) {
                        shaiba.body.applyLinearImpulse(bita0.getImpulse(), shaiba.body.getPosition(), true);
                    }
                } else {
                    bita1.setOldXY();
                    bita1.body.setTransform(touch.x, touch.y, 0);
                    if (bita1.contact(shaiba)) {
                        shaiba.body.applyLinearImpulse(bita1.getImpulse(), shaiba.body.getPosition(), true);
                    }
                }
            }
        }
		// события
        world.step(1 / 60f, 6, 2);

		// отрисовка
        //debugRenderer.render(world, camera.combined);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		batch.draw(imgField, 0, 0, WIDTH, HEIGHT);
		batch.draw(imgBita0, bita0.scrX(), bita0.scrY(), bita0.r*2, bita0.r*2);
		batch.draw(imgBita1, bita1.scrX(), bita1.scrY(), bita1.r*2, bita1.r*2);
		batch.draw(imgShayba, shaiba.scrX(), shaiba.scrY(), shaiba.r*2, shaiba.r*2);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
