package app.evoMouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.input;

public class GameScreen extends ScreenAdapter {
    public static final int WIDTH = 320 * 4;
    public static final int HEIGHT = 180 * 4;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private IsometricRenderer renderer;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(WIDTH / 2 - 500, HEIGHT / 2, 10);

        renderer = new IsometricRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        camera.update();

        handleInput();

        batch.begin();

        renderer.drawGround(batch);

        batch.end();
    }

    private void handleInput() {
        if(input.isKeyPressed(Input.Keys.A)){
            camera.zoom -= 0.002f;
        } else if (input.isKeyPressed(Input.Keys.D)) {
            camera.zoom += 0.002f;
        }

        if(input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= 1;
        } else if (input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += 1;
        } else if (input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y -= 1;
        } else if (input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y += 1;
        }
    }

    @Override
    public void dispose(){

    }
}
