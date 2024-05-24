package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class UnmoveableBlock implements Block {
    private World world;
    private Body body;

    private Texture texture;
    private GameScreen game;

    public UnmoveableBlock(GameScreen game, World world, float x, float y, String textureName) {
        this.world = world;
        this.game = game;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();

        this.texture = new Texture("tiles/" + textureName);

        this.body.setUserData("block");
    }

    @Override
    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    @Override
    public Body getBody() {
        return this.body;
    }


    @Override
    public void render(SpriteBatch batch) {
        float xUnit = 32;
        float yUnit = 32;

        float rawX = this.getPosition().x;
        float rawY = this.getPosition().y;

        float finalX = rawX * xUnit;
        float finalY = rawY * yUnit;

        batch.draw(this.texture, finalX, finalY);
    }

    @Override
    public void die() {

    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
