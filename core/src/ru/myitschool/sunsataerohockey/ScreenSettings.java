package ru.myitschool.sunsataerohockey;

import static ru.myitschool.sunsataerohockey.MyGame.HEIGHT;
import static ru.myitschool.sunsataerohockey.MyGame.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenSettings implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton btnExit, btnSound;

    public ScreenSettings(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("hockey.jpg");
        btnSound = new AeroButton(mg.font, "Sound on", 500, 400);
        btnExit = new AeroButton(mg.font, "Back", 500, 300);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            mg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mg.camera.unproject(mg.touch);
            if(btnSound.hit(mg.touch.x, mg.touch.y)){
                mg.soundOn = !mg.soundOn;
                if(mg.soundOn) btnSound.text = "Sound: ON";
                else btnSound.text = "Sound: OFF";
            }
            if (btnExit.hit(mg.touch.x, mg.touch.y)) {
                mg.setScreen(mg.screenIntro);
            }
        }


        // отрисовка
        //debugRenderer.render(world, camera.combined);
        // рисуем мир
        mg.camera.update();
        mg.batch.setProjectionMatrix(mg.camera.combined);
        mg.batch.begin();
        mg.batch.draw(imgBackGround, 0, 0, WIDTH, HEIGHT);
        mg.batch.end();
        // рисуем тексты
        mg.cameraFont.update();
        mg.batch.setProjectionMatrix(mg.cameraFont.combined);
        mg.batch.begin();
        btnSound.font.draw(mg.batch, btnSound.text, btnSound.x, btnSound.y);
        btnExit.font.draw(mg.batch, btnExit.text, btnExit.x, btnExit.y);
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

    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
    }
}
