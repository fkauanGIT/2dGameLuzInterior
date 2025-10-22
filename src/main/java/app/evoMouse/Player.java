package app.evoMouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player implements Entity{
    private Texture img;
    private Vector2 pos;
    private float time;

    public Player() {
        img = new Texture(Gdx.files.internal("assets/mouse.png"));
        pos = new Vector2(8, 38);
    }

    @Override
    public void render(SpriteBatch batch) {
        float scale = 0.1f;
        batch.draw(img, pos.x, pos.y, img.getWidth() * scale,img.getHeight() * scale);
    }

    @Override
    public void update(float delta) {
        move();
    }

    public void move() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            pos.x -= 32;
            pos.y += 16;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            pos.x += 32;
            pos.y -= 16;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            pos.x -= 32;
            pos.y -= 16;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            pos.x += 32;
            pos.y += 16;
        }
    }
}
