package ru.myitschool.sunsataerohockey;


import com.badlogic.gdx.graphics.Texture;

public class AeroButton {
    float x, y;
    float width, height;

    public AeroButton(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    boolean hit(float tx, float ty){
        return x < tx && tx < x+width && y < ty && ty < y+height;
    }
}
