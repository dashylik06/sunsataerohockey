package ru.myitschool.sunsataerohockey;

import static ru.myitschool.sunsataerohockey.MyGame.HEIGHT;
import static ru.myitschool.sunsataerohockey.MyGame.WIDTH;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ScreenIntro implements Screen {
    MyGame mg;

    Texture imgBackGround; // фон
    AeroButton btnGame, btnSettings, btnAbout, btnExit;

    public ScreenIntro(MyGame myGame) {
        mg = myGame;
        imgBackGround = new Texture("backgrounds/bg_intro.jpg");
    }
