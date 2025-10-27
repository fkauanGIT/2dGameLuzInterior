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
 * Representa o jogador na cena isométrica, gerenciando posição, animações de movimento e idle.
 * Controla atualizações da posição a cada frame conforme entrada do teclado (WASD) e renderiza
 * o sprite animado na tela.
 */
public class Player implements Entity {

    /** Animações para caminhar nas quatro direções */
    private final Animation<TextureRegion> walkUp, walkDown, walkLeft, walkRight;

    /** Animações para estado parado (‘idle’) nas quatro direções */
    private final Animation<TextureRegion> idleUp, idleDown, idleLeft, idleRight;

    /** Tempo acumulado para controle do frame atual da animação */
    private float stateTime;

    /** Animação atualmente em execução */
    private Animation<TextureRegion> currentAnimation;

    /** Indica se o jogador está se movendo neste frame */
    private boolean moving;

    /** Coordenadas posicionais do jogador no espaço isométrico */
    private final Vector2 pos;

    /** Velocidade de movimento em píxels por frame */
    private final float speed = 2f;

    /**
     * Inicializa o jogador na posição (0,0) e carrega as animações de movimento e idle.
     * Define a animação inicial como 'idle' para baixo.
     */
    public Player() {
        pos = new Vector2(0, 0);
        stateTime = 0f;

        walkUp = loadAnimation("assets/sprite_player/top/walk", 7, 0.1f);
        walkDown = loadAnimation("assets/sprite_player/down/walk", 7, 0.1f);
        walkLeft = loadAnimation("assets/sprite_player/left/walk", 7, 0.1f);
        walkRight = loadAnimation("assets/sprite_player/right/walk", 7, 0.1f);

        idleUp = loadAnimation("assets/sprite_player/top/idle", 7, 0.1f);
        idleDown = loadAnimation("assets/sprite_player/down/idle", 7, 0.1f);
        idleLeft = loadAnimation("assets/sprite_player/left/idle", 7, 0.1f);
        idleRight = loadAnimation("assets/sprite_player/right/idle", 7, 0.1f);

        currentAnimation = idleDown;
    }

    /**
     * Atualiza o estado do jogador, processando entrada e ajustando posição e animação.
     * Movimentos combinam deslocamentos diagonais para simular visão isométrica.
     * Atualiza o tempo da animação para controlar o frame corrente.
     *
     * @param delta tempo em segundos desde a última atualização (frame)
     */
    @Override
    public void update(float delta) {
        moving = false;
        Vector2 direction = new Vector2();

        // Captura das teclas — cada uma define uma direção
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // Diagonal cima-direita
            direction.x -= 0;
            direction.y += 1;
            currentAnimation = walkUp;
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            // Diagonal baixo-esquerda
            direction.x -= 0;
            direction.y -= 1;
            currentAnimation = walkDown;
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Esquerda (pura)
            direction.x -= 1;
            direction.y += 0; // Mantém
            currentAnimation = walkLeft;
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Direita (pura)
            direction.x += 1;
            direction.y -= 0;
            currentAnimation = walkRight;
            moving = true;
        }

        // Normaliza direção (para não andar mais rápido na diagonal)
        if (!direction.isZero()) {
            direction.nor();
            pos.add(direction.scl(speed));
        }

        // Se não estiver a mover, muda para animação 'idle'
        if (!moving) {
            if (currentAnimation == walkUp) currentAnimation = idleUp;
            else if (currentAnimation == walkDown) currentAnimation = idleDown;
            else if (currentAnimation == walkLeft) currentAnimation = idleLeft;
            else if (currentAnimation == walkRight) currentAnimation = idleRight;
        }

        // Atualiza o tempo da animação
        stateTime += delta;
    }


    /**
     * Renderiza o sprite do jogador na posição atual utilizando a animação corrente.
     * Ajusta escala e posicionamento para centralizar corretamente no tile isométrico.
     *
     * @param batch objeto utilizado para desenhar gráficos na tela
     */
    @Override
    public void render(SpriteBatch batch) {
        float scale = 1.5f;
        TextureRegion frame = currentAnimation.getKeyFrame(stateTime, true);

        float spriteWidth = frame.getRegionWidth() * scale;
        float spriteHeight = frame.getRegionHeight() * scale;

        float drawX = pos.x + (TILE_WIDTH / 2f) - (spriteWidth / 2f);
        float drawY = pos.y + TILE_HEIGHT - (spriteHeight - 25f);

        batch.draw(frame, drawX, drawY, spriteWidth, spriteHeight);
    }

    /**
     * Carrega uma animação a partir da sequência de imagens no caminho especificado.
     *
     * @param path diretório base dos frames da animação
     * @param frameCount quantidade de frames na animação
     * @param frameDuration duração de cada frame em segundos
     * @return animação construída com os frames carregados
     */
    private Animation<TextureRegion> loadAnimation(String path, int frameCount, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = new TextureRegion(new Texture(Gdx.files.internal(path + "/" + i + ".png")));
        }
        return new Animation<>(frameDuration, frames);
    }

    /**
     * Retorna a coordenada X isométrica atual do jogador.
     *
     * @return posição X no espaço isométrico
     */
    public float getIsoX() { return pos.x; }

    /**
     * Retorna a coordenada Y isométrica atual do jogador.
     *
     * @return posição Y no espaço isométrico
     */
    public float getIsoY() { return pos.y; }
}