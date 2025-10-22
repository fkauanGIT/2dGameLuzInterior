package app.evoMouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class IsometricRenderer {

    private int[][] map;

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    public Texture grass;
    public Texture grass_2;
    public Texture grass_3;

    public IsometricRenderer() {
        grass = new Texture(Gdx.files.internal("assets/grass.png"));
        grass_2= new Texture(Gdx.files.internal("assets/grass_2.png"));
//        grass_3= new Texture(Gdx.files.internal("assets/tree1.png"));
        map = generateMap();
    }

    public void drawGround(SpriteBatch batch) {
        for (int row = map.length - 1; row >= 0; row--) {
            for (int col = map.length - 1; col >= 0; col--) {
                float x = (col - row) * (TILE_WIDTH / 2f);
                float y = (col + row) * (TILE_HEIGHT / 4f);

                if(map[row][col] == 1) {
                    batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT);
                } else if (map[row][col] == 0) {
                    batch.draw(grass_2, x, y, TILE_WIDTH, TILE_HEIGHT);
                }

//                if (map[row][col] == 2) {
//                    batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT); // chão base
//                    batch.draw(grass_3, x, y + TILE_HEIGHT / 2f, TILE_WIDTH, TILE_HEIGHT * 2f);
//                }
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            map = generateMap();
        }
    }

    private int[][] generateMap() {

        Random r = new Random();
        int rSize = r.nextInt(25);

        if(rSize < 10) {
            rSize = 10;
        }

        int[][] map = new int[rSize][rSize];

        for(int row = 0; row < map.length; row++) {
            for(int col = 0; col < map.length; col++) {
                int num = r.nextInt(10);

                if(num < 2) {
                    map[row][col] = 0;
                }else {
                    map[row][col] = 1;
                }
            }
        }

        map[0][0] = 1;
        return map;
    }
}
