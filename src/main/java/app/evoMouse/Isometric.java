package app.evoMouse;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Isometric extends Game {

    private SpriteBatch batch;
    private GameScreen gScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gScreen = new GameScreen(batch);
        setScreen(gScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
