package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Character implements Block {
    private World world;
    private Body body;

    private GameScreen game;

    private Texture[] textures;

    private float frame = 0;


    public Character(GameScreen game, World world, float x, float y) {
        this.world = world;
        this.game = game;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);

        this.body.setLinearDamping(0f);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = .5f;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0f; // Some bounciness

        this.body.createFixture(fixtureDef);
        shape.dispose();

        this.textures = Tools.loadTextures(6,"characters/");

        this.body.setUserData("character");
    }

    @Override
    public Vector2 getPosition() {
        return this.body.getPosition();
    }


    @Override
    public Body getBody() {
        return this.body;
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        this.frame += (10 * dt);
        if (this.frame >= 6) this.frame = 0;

        if (this.getPosition().x > 20 && this.getPosition().x < 21) {

            this.body.applyLinearImpulse(new Vector2(5, 5), this.body.getWorldCenter(), true);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        this.update();

        float xUnit = 32;
        float yUnit = 32;

        float rawX = this.getPosition().x;
        float rawY = this.getPosition().y;

        float finalX = rawX * xUnit;
        float finalY = rawY * yUnit;

        batch.draw(this.textures[(int)this.frame], finalX-32, finalY-32);
    }

    @Override
    public void die() {
    }

    @Override
    public void dispose() {
        for (int i = 0; i < 7; i++) {
            this.textures[i].dispose();
        }
    }
}
