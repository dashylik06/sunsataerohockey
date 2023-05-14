package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class MyGame extends Game {
    public static final float WIDTH = 12.8f, HEIGHT = 7.2f;
    SpriteBatch batch;
    OrthographicCamera camera, cameraFont;
    Vector3 touch;
    BitmapFont font, fontLarge;

    ScreenIntro screenIntro;
    ScreenSettings screenSettings;
    ScreenAbout screenAbout;
    ScreenGame screenGame;
    boolean soundOn = true;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        cameraFont = new OrthographicCamera();
        cameraFont.setToOrtho(false, WIDTH*100, HEIGHT*100);
        touch = new Vector3();
        Box2D.init();

        createFont();

        screenIntro = new ScreenIntro(this);
        screenSettings = new ScreenSettings(this);
        screenAbout = new ScreenAbout(this);
        screenGame = new ScreenGame(this);
        setScreen(screenIntro);

    }

    public void createFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("text.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
        parameter.size = 50;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);

        parameter.size = 50;
        fontLarge = generator.generateFont(parameter);

        generator.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    void sleep(){
        try {
            Thread.sleep(500);
        } catch (Exception e){

        }
    }
}
