package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenSettings implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton  btnExit, btnSound;

    public ScreenSettings(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("hockey.jpg");
        btnSound = new AeroButton(mg.font, "Sound on", 500, 500);
        btnExit = new AeroButton(mg.font, "Back", 500, 200);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
