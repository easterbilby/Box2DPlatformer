package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

public class MoveableBlock implements Block {
    private World world;
    private Body body;

    private Texture texture;
    private Texture textureSelected;
    private Texture shadow;

    private boolean isSelected = false;

    private GameScreen game;

    public MoveableBlock(GameScreen game, World world, float x, float y, String texture, float restitution) {
        this.world = world;
        this.game = game;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f, 1f); // Creates a 2m x 2m block

        this.body.setLinearDamping(.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.1f; // Low friction for ice
        fixtureDef.restitution = 0.5f; // Some bounciness

        body.createFixture(fixtureDef);
        shape.dispose();

        this.texture = new Texture("planetcute/Ice Block.png");
        this.textureSelected = new Texture("planetcute/Ice Block Selected.png");
        this.shadow = new Texture("planetcute/Shadow.png");

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

    public void update() {

    }


    @Override
    public void render(SpriteBatch batch) {
        this.update();

        float xUnit = 50;
        float yUnit = 40;

        //float centerX = Gdx.graphics.getWidth() / 2;

        float rawX = this.getPosition().x;
        float rawY = this.getPosition().y;

        float finalX = rawX * xUnit;
        float finalY = rawY * yUnit;

        batch.draw(this.texture, finalX-51, finalY-80);


    }


    @Override
    public void die() {

    }

    @Override
    public void dispose() {
        this.texture.dispose();
        this.shadow.dispose();
    }
}
