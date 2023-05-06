package ru.myitschool.sunsataerohockey;

import static ru.myitschool.sunsataerohockey.MyGame.HEIGHT;
import static ru.myitschool.sunsataerohockey.MyGame.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;

public class ScreenIntro implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton btnGame, btnSettings, btnAbout, btnExit;

    public ScreenIntro(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("hockey.jpg");
        btnGame = new AeroButton(mg.fontLarge, "PLAY", 500, 450);
        btnSettings = new AeroButton(mg.fontLarge, "SETTINGS", 500, 350);
        btnAbout = new AeroButton(mg.fontLarge, "ABOUT", 500, 250);
        btnExit = new AeroButton(mg.fontLarge, "EXIT", 500, 150);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            mg.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mg.camera.unproject(mg.touch);
            if (btnGame.hit(mg.touch.x, mg.touch.y)) {
                mg.sleep();
                mg.setScreen(mg.screenGame);
            }
            if (btnSettings.hit(mg.touch.x, mg.touch.y)) {
                mg.setScreen(mg.screenSettings);
            }
            if (btnAbout.hit(mg.touch.x, mg.touch.y)) {
                mg.setScreen(mg.screenAbout);
            }
            if (btnExit.hit(mg.touch.x, mg.touch.y)) {
                Gdx.app.exit();
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
        btnGame.font.draw(mg.batch, btnGame.text, btnGame.x, btnGame.y);
        btnSettings.font.draw(mg.batch, btnSettings.text, btnSettings.x, btnSettings.y);
        btnAbout.font.draw(mg.batch, btnAbout.text, btnAbout.x, btnAbout.y);
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
