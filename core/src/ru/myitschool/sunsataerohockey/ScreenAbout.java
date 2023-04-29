package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenAbout implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton btnGame, btnSettings, btnAbout, btnExit;

    public ScreenAbout(MyGame myGame) {
        mg = myGame;
        //imgBackGround = new Texture("backgrounds/bg_intro.jpg");
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
