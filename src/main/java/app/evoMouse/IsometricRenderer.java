package app.evoMouse;

import app.evoMouse.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Classe responsável por renderizar o mapa isométrico e seus elementos no jogo EvoMouse.
 * <p>
 * A {@code IsometricRenderer} gerencia:
 * <ul>
 *   <li>A geração procedural do mapa 2D (matriz de inteiros);</li>
 *   <li>O desenho de tiles de chão e elementos como árvores e troncos;</li>
 *   <li>A renderização do jogador na camada correta, respeitando a profundidade isométrica;</li>
 *   <li>A atualização dinâmica do mapa ao pressionar a tecla {@code G}.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Cada tile é representado por um valor numérico:
 * <ul>
 *   <li>0 → {@code grass_2}</li>
 *   <li>1 → {@code grass}</li>
 *   <li>2 → {@code tree_1}</li>
 *   <li>3 → {@code tree_2}</li>
 *   <li>4 → {@code tronco}</li>
 *   <li>5 → {@code grass_3}</li>
 * </ul>
 * </p>
 *
 * @author
 * @version 1.2
 * @since 2025
 */
public class IsometricRenderer {

    /** Mapa bidimensional representando cada tile do terreno. */
    private int[][] map;

    /** Largura padrão de cada tile em pixels. */
    public static final int TILE_WIDTH = 64;

    /** Altura padrão de cada tile em pixels. */
    public static final int TILE_HEIGHT = 64;

    /** Texturas utilizadas para o terreno e elementos do cenário. */
    public Texture grass, grass_2, grass_3, tree_1, tree_2, tronco;

    /**
     * Construtor padrão que inicializa todas as texturas e gera o primeiro mapa procedural.
     */
    public IsometricRenderer() {
        grass = new Texture(Gdx.files.internal("assets/grass_1.png"));
        grass_2 = new Texture(Gdx.files.internal("assets/grass_2.png"));
        grass_3 = new Texture(Gdx.files.internal("assets/grass_3.png"));
        tree_1 = new Texture(Gdx.files.internal("assets/tree-1.png"));
        tree_2 = new Texture(Gdx.files.internal("assets/tree-2.png"));
        tronco = new Texture(Gdx.files.internal("assets/tronco.png"));
        map = generateMap();
    }

    /**
     * Renderiza o mapa completo e o jogador no contexto isométrico.
     *
     * <p>
     * O método desenha os tiles linha a linha, de trás para frente, para garantir a
     * sobreposição correta dos elementos (profundidade visual isométrica).
     * </p>
     *
     * @param batch  instância de {@link SpriteBatch} utilizada para desenhar os elementos.
     * @param player instância do jogador atual a ser desenhado.
     */
    public void drawGround(SpriteBatch batch, Player player) {
        for (int row = map.length - 1; row >= 0; row--) {
            for (int col = map.length - 1; col >= 0; col--) {

                float x = (col - row) * (TILE_WIDTH / 2f);
                float y = (col + row) * (TILE_HEIGHT / 4f);

                // Renderização dos tipos de chão
                switch (map[row][col]) {
                    case 0 -> batch.draw(grass_2, x, y, TILE_WIDTH, TILE_HEIGHT);
                    case 1 -> batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT);
                    case 5 -> batch.draw(grass_3, x, y, TILE_WIDTH, TILE_HEIGHT);
                }

                // Renderização de elementos (árvores, troncos)
                if (map[row][col] == 2) {
                    batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT);
                    batch.draw(tree_1, x, y + TILE_HEIGHT / 1.5f, TILE_WIDTH, TILE_HEIGHT + 30f);
                } else if (map[row][col] == 3) {
                    batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT);
                    batch.draw(tree_2, x, y + TILE_HEIGHT / 1.5f, TILE_WIDTH, TILE_HEIGHT + 30f);
                } else if (map[row][col] == 4) {
                    batch.draw(grass, x, y, TILE_WIDTH, TILE_HEIGHT);
                    batch.draw(tronco, x, y + TILE_HEIGHT / 1.5f, TILE_WIDTH, TILE_HEIGHT / 2f);
                }

                // Renderiza o jogador quando ele está dentro do tile atual (ajuste simples de profundidade)
                if (Math.abs(player.getIsoX() - x) < TILE_WIDTH / 2f &&
                        Math.abs(player.getIsoY() - y) < TILE_HEIGHT / 2f) {
                    player.render(batch);
                }
            }
        }

        // Gera novo mapa ao pressionar 'G'
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            map = generateMap();
        }
    }

    /**
     * Gera um novo mapa procedural com distribuição aleatória de tiles.
     * <p>
     * O tamanho do mapa varia de 10 a 50 tiles por dimensão.
     * As probabilidades determinam o tipo de terreno ou objeto em cada célula.
     * </p>
     *
     * @return matriz bidimensional representando o mapa isométrico.
     */
    private int[][] generateMap() {
        Random r = new Random();
        int rSize = Math.max(10, r.nextInt(50)); // tamanho mínimo de 10x10

        int[][] map = new int[rSize][rSize];

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                int num = r.nextInt(100);

                if (num < 15) map[row][col] = 0;     // 15% → grass_2
                else if (num < 70) map[row][col] = 1; // 55% → grass
                else if (num < 85) map[row][col] = 5; // 15% → grass_3
                else if (num < 93) map[row][col] = 2; // 8%  → tree_1
                else if (num < 98) map[row][col] = 3; // 5%  → tree_2
                else map[row][col] = 4;               // 2%  → tronco
            }
        }

        // Tile inicial garantido como chão
        map[0][0] = 1;
        return map;
    }

    public void dispose() {
        grass.dispose();
        grass_2.dispose();
        grass_3.dispose();
        tree_1.dispose();
        tree_2.dispose();
        tronco.dispose();
    }
}
