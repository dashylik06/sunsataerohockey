package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenSettings implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton btnExit, btnSound, btnMusic;

    public ScreenSettings(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("hockey.jpg");
        btnSound = new AeroButton(mg.font, "Sound on", 500, 500);
        btnExit = new AeroButton(mg.font, "Back", 500, 200);
        btnMusic = new AeroButton(mg.font, "Music on", 500, 200);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            mg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mg.camera.unproject(mg.touch);
            if (btnMusic.hit(mg.touch.x, mg.touch.y)) {
                mg.screenGame.musicOn = !mg.screenGame.musicOn;
                if (mg.screenGame.musicOn) {
                    btnMusic.text = "Music on";
                } else {
                    btnMusic.text = "Music off";
                }
            }
        }
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
