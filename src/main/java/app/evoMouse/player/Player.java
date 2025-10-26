package app.evoMouse.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static app.evoMouse.IsometricRenderer.TILE_HEIGHT;
import static app.evoMouse.IsometricRenderer.TILE_WIDTH;

/**
 * Representa o jogador controlável no mundo isométrico do jogo EvoMouse.
 * <p>
 * A classe {@code Player} é responsável por:
 * <ul>
 *   <li>Gerenciar a posição e o movimento do personagem no espaço isométrico;</li>
 *   <li>Renderizar o sprite do jogador na tela;</li>
 *   <li>Interpretar comandos de entrada do teclado (W, A, S, D).</li>
 * </ul>
 * </p>
 *
 * <p>
 * A movimentação é discreta, ou seja, ocorre a cada toque em uma tecla.
 * O deslocamento segue o padrão isométrico, deslocando-se em 32px no eixo X
 * e 16px no eixo Y para manter a perspectiva.
 * </p>
 *
 * @author
 * @version 1.0
 * @since 2025
 */
public class Player implements Entity {

    /**
     * Animação do jogador parado para cima.
     * <p>
     * Cada frame é carregado de um arquivo separado (0.png a 7.png).
     * </p>
     */
    private final Animation<TextureRegion> walkUpAnimation;
    /**
     * Tempo acumulado desde o início da animação.
     * <p>
     * É usado para determinar qual frame da animação deve ser exibido.
     * </p>
     */
    private float stateTime;


    /**
     * Posição atual do jogador no mundo isométrico.
     */
    private final Vector2 pos;

    /**
     * Construtor padrão que inicializa o sprite e define a posição inicial do jogador.
     */
    public Player() {

        TextureRegion[] frames = new TextureRegion[8];
        frames[0] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/0.png")));
        frames[1] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/1.png")));
        frames[2] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/2.png")));
        frames[3] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/3.png")));
        frames[4] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/4.png")));
        frames[5] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/5.png")));
        frames[6] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/6.png")));
        frames[7] = new TextureRegion(new Texture(Gdx.files.internal("assets/sprite_player/top/idle/7.png")));

        // Cria a animação com duração de 0.45 segundos por frame
        walkUpAnimation = new Animation<>(0.45f, frames);
        stateTime = 0f;

        pos = new Vector2(0, 0);
    }

    /**
     * Renderiza o jogador na tela, aplicando escala ao sprite.
     *
     * @param batch instância de {@link SpriteBatch} usada para desenhar texturas.
     */

    /**

     Renderiza o sprite escalado e posicionado centralmente em relação a um tile.

     <p>
     A imagem é desenhada com escala fixa de 2x, com o eixo X centralizado no meio da tile e o eixo Y ajustado para alinhar visualmente a sprite à tile.

     </p>
     @param batch o objeto SpriteBatch utilizado para desenhar o sprite na tela
     */

    @Override
    public void render(SpriteBatch batch) {
        float scale = 1.5f;

        TextureRegion currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
        float spriteWidth = currentFrame.getRegionWidth() * scale;
        float spriteHeight = currentFrame.getRegionHeight() * scale;

        float drawX = pos.x + (TILE_WIDTH / 2f) - (spriteWidth / 2f);
        float adjustedDrawY = pos.y + TILE_HEIGHT - (spriteHeight - 32f);

        batch.draw(currentFrame, drawX, adjustedDrawY, spriteWidth, spriteHeight);
    }

    /**
     * Atualiza o estado do jogador.
     * <p>
     * Chamado a cada frame do jogo, delegando o controle à função {@link #move()}.
     * </p>
     *
     * @param delta tempo decorrido entre frames, útil para movimentação baseada em tempo (não utilizada aqui).
     */
    @Override
    public void update(float delta) {
        stateTime += delta;
        move();
    }

    /**
     * Controla o movimento do jogador com base nas teclas pressionadas.
     * <p>
     * Cada tecla movimenta o jogador em uma direção isométrica:
     * <ul>
     *   <li>W → cima esquerda</li>
     *   <li>S → baixo direita</li>
     *   <li>A → baixo esquerda</li>
     *   <li>D → cima direita</li>
     * </ul>
     * </p>
     */
    public void move() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            pos.x -= 32;
            pos.y += 16;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            pos.x += 32;
            pos.y -= 16;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            pos.x -= 32;
            pos.y -= 16;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            pos.x += 32;
            pos.y += 16;
        }
    }

    /**
     * Obtém a coordenada X isométrica atual do jogador.
     *
     * @return valor de X em coordenadas isométricas.
     */
    public float getIsoX() {
        return pos.x;
    }

    /**
     * Obtém a coordenada Y isométrica atual do jogador.
     *
     * @return valor de Y em coordenadas isométricas.
     */
    public float getIsoY() {
        return pos.y;
    }
}
