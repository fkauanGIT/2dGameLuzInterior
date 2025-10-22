package app.evoMouse;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    public void render(SpriteBatch batch);
    public void update(float delta);
}
