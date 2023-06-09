package ru.myitschool.sunsataerohockey;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class KinematicBodyBall {
    Body body;
    float r;
    float oldX, oldY;

    KinematicBodyBall(World world, float x, float y, float radius) {
        r = radius;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    boolean hit(float tx, float ty) {
        return Math.pow(tx-getX(),2) + Math.pow(ty-getY(),2) < r*r;
    }

    float getX(){
        return body.getPosition().x;
    }

    float getY(){
        return body.getPosition().y;
    }

    float scrX(){
        return body.getPosition().x-r;
    }

    float scrY(){
        return body.getPosition().y-r;
    }

    boolean contact(DynamicBodyBall sh) {
        return Math.pow(getX()-sh.getX(), 2) + Math.pow(getY()-sh.getY(), 2) <= Math.pow(r+sh.r, 2);
    }

    Vector2 getImpulse() {
        return new Vector2((getX()-oldX)*8, (getY()-oldY)*8);
    }

    void setOldXY() {
        oldX = getX();
        oldY = getY();
    }
}
