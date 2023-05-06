package ru.myitschool.sunsataerohockey;

import static ru.myitschool.sunsataerohockey.MyGame.HEIGHT;
import static ru.myitschool.sunsataerohockey.MyGame.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenAbout implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton  btnExit;
    String textAbout = "Игра sunsataerohockey создана\n "+
            " в itschool samsung.\n"+
            "Цель игры: забить шайбу в ворота противника";

    public ScreenAbout(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("hockey.jpg");
        btnExit = new AeroButton(mg.fontLarge, "BACK", 500, 150);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            mg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mg.camera.unproject(mg.touch);

            if(btnExit.hit(mg.touch.x, mg.touch.y)){
                mg.setScreen(mg.screenIntro);
            }
        }

        mg.camera.update();
        mg.batch.setProjectionMatrix(mg.camera.combined);
        mg.batch.begin();
        mg.batch.draw(imgBackGround, 0, 0, WIDTH, HEIGHT);
        mg.font.draw(mg.batch, textAbout, 400, 650);
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

    }
}
