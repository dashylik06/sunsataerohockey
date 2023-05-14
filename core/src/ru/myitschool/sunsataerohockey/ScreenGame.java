package ru.myitschool.sunsataerohockey;

import static ru.myitschool.sunsataerohockey.MyGame.HEIGHT;
import static ru.myitschool.sunsataerohockey.MyGame.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGame implements Screen {
    public boolean musicOn;
    MyGame mg;
    World world;
    Box2DDebugRenderer debugRenderer;

    Texture imgField;
    Texture imgBita0, imgBita1;
    Texture imgShayba;

    Sound sndShaiba, sndWin;

    StaticBodyBox wallTop, wallDown;
    StaticBodyBox wallLeftT, wallLeftD, wallRightT, wallRightD;
    DynamicBodyBall shaiba;
    KinematicBodyBall bita0, bita1;



    long timeGoal, timeInterval = 2500;
    boolean isGoal;

    int goal0, goal1;

    public ScreenGame(MyGame myGame) {
        mg = myGame;
        world = new World(new Vector2(0, 0), false);
        debugRenderer = new Box2DDebugRenderer();

        imgField = new Texture("field.png");
        imgBita0 = new Texture("BitaC.png");
        imgBita1 = new Texture("BitaG.png");
        imgShayba = new Texture("shaiba.png");
        sndShaiba = Gdx.audio.newSound(Gdx.files.internal("audio.mp3"));
        sndWin = Gdx.audio.newSound(Gdx.files.internal("win.mp3"));

        wallTop = new StaticBodyBox(world, WIDTH / 2, HEIGHT, WIDTH, 0.4f);
        wallDown = new StaticBodyBox(world, WIDTH / 2, 0, WIDTH, 0.4f);

        wallLeftT = new StaticBodyBox(world, 0, HEIGHT, 0.4f, HEIGHT/6*4);
        wallLeftD = new StaticBodyBox(world, 0, 0, 0.4f, HEIGHT/6*4);

        wallRightT = new StaticBodyBox(world, WIDTH, HEIGHT, 0.4f, HEIGHT/6*4);
        wallRightD = new StaticBodyBox(world, WIDTH, 0, 0.4f, HEIGHT/6*4);

        shaiba = new DynamicBodyBall(world, WIDTH / 2, HEIGHT / 2, 0.5f);
        bita0 = new KinematicBodyBall(world, WIDTH / 8, HEIGHT / 2, 0.5f);
        bita1 = new KinematicBodyBall(world, WIDTH / 8 * 7, HEIGHT / 2, 0.5f);
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void render(float delta) {
// касания
        if (Gdx.input.justTouched()) {
            mg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mg.camera.unproject(mg.touch);}
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                mg.setScreen(mg.screenIntro);}


        for (int i = 0; i < 2; i++) {
            if (Gdx.input.isTouched(i)) {
                mg.touch.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                mg.camera.unproject(mg.touch);
                if (mg.touch.x < WIDTH / 2) {
                    bita0.setOldXY();
                    bita0.body.setTransform(mg.touch.x, mg.touch.y, 0);
                    if (bita0.contact(shaiba)) {
                        shaiba.body.applyLinearImpulse(bita0.getImpulse(), shaiba.body.getPosition(), true);
                        if(mg.soundOn) sndShaiba.play();
                    }
                } else {
                    bita1.setOldXY();
                    bita1.body.setTransform(mg.touch.x, mg.touch.y, 0);
                    if (bita1.contact(shaiba)) {
                        shaiba.body.applyLinearImpulse(bita1.getImpulse(), shaiba.body.getPosition(), true);
                        if(mg.soundOn) sndShaiba.play();
                    }
                }
            }
        }
        // события
        world.step(1 / 60f, 6, 2);
        if(isGoal) {
            if (TimeUtils.millis() > timeGoal + timeInterval) {
                isGoal = false;
                shaiba.body.setTransform(WIDTH / 2, HEIGHT / 2, 0);
                shaiba.body.setLinearVelocity(0,0);
                bita0.body.setTransform(WIDTH / 8, HEIGHT / 2, 0);
                bita1. body.setTransform(WIDTH / 8 * 7, HEIGHT / 2, 0);
            }
        }
        else {
            if (shaiba.getX() < 0) {
                goal1 += 1;
                isGoal = true;
                timeGoal = TimeUtils.millis();
                if(mg.soundOn) sndWin.play();

            }
            if (shaiba.getX() > WIDTH) {
                goal0 += 1;
                isGoal = true;
                timeGoal = TimeUtils.millis();
                if(mg.soundOn) sndWin.play();
            }
        }


        // отрисовка
        //debugRenderer.render(world, camera.combined);
        // рисуем мир
        mg.camera.update();
        mg.batch.setProjectionMatrix(mg.camera.combined);
        mg.batch.begin();
        mg.batch.draw(imgField, 0, 0, WIDTH, HEIGHT);
        mg.batch.draw(imgBita0, bita0.scrX(), bita0.scrY(), bita0.r*2, bita0.r*2);
        mg.batch.draw(imgBita1, bita1.scrX(), bita1.scrY(), bita1.r*2, bita1.r*2);
        mg.batch.draw(imgShayba, shaiba.scrX(), shaiba.scrY(), shaiba.r*2, shaiba.r*2);
        mg.batch.end();
        // рисуем тексты
        mg.cameraFont.update();
        mg.batch.setProjectionMatrix(mg.cameraFont.combined);
        mg.batch.begin();
        mg.fontLarge.draw(mg.batch, ""+goal0, 0, HEIGHT*100-30, WIDTH/2*100-20, Align.right, true);
        mg.fontLarge.draw(mg.batch, ":", 0, HEIGHT*100-25, WIDTH*100, Align.center, true);
        mg.fontLarge.draw(mg.batch, ""+goal1, WIDTH/2*100+20, HEIGHT*100-30, WIDTH/2*100-20, Align.left, true);
        mg.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setCatchKey(Input.Keys.BACK, false);
    }

    @Override
    public void dispose() {
        imgField.dispose();
        imgBita0.dispose();
    }
}
