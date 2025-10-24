package app.evoMouse;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * {@code Isometric} é a classe principal do jogo <b>EvoMouse</b>.
 *
 * <p>
 * Responsável pelo ciclo de vida da aplicação, inicialização de recursos globais
 * e controle da tela ativa ({@link GameScreen}). Esta classe estende {@link Game},
 * que gerencia o sistema de telas (screens) e facilita a troca entre elas.
 * </p>
 *
 * <p><b>Funções principais:</b></p>
 * <ul>
 *   <li>Inicializar o {@link SpriteBatch}, responsável pela renderização de sprites.</li>
 *   <li>Instanciar e definir a tela principal do jogo ({@link GameScreen}).</li>
 *   <li>Delegar o ciclo de renderização para a tela atual.</li>
 *   <li>Gerenciar a liberação de recursos ao encerrar o jogo.</li>
 * </ul>
 */
public class Isometric extends Game {

    /** Gerenciador global de renderização 2D. */
    private SpriteBatch batch;

    /** Tela principal do jogo, responsável pela renderização isométrica. */
    private GameScreen gScreen;

    /**
     * Inicializa os recursos essenciais do jogo.
     *
     * <p>
     * Este método é chamado automaticamente pelo framework LibGDX
     * quando a aplicação é criada. Aqui, o {@link SpriteBatch} é inicializado,
     * a tela principal ({@link GameScreen}) é instanciada, e o jogo é configurado
     * para exibi-la.
     * </p>
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        gScreen = new GameScreen(batch);
        setScreen(gScreen);
    }

    /**
     * Realiza o ciclo de renderização do jogo.
     *
     * <p>
     * Este método delega a responsabilidade de desenhar e atualizar o jogo
     * para a tela atualmente ativa, por meio da chamada à implementação padrão
     * de {@link Game#render()}.
     * </p>
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Libera os recursos utilizados pelo jogo ao final de sua execução.
     *
     * <p>
     * Este método deve ser chamado apenas quando a aplicação for encerrada.
     * Ele garante que os recursos nativos (como texturas, sons e fontes)
     * sejam devidamente liberados, evitando vazamentos de memória.
     * </p>
     *
     * <p><b>Importante:</b></p>
     * <ul>
     *   <li>LibGDX não realiza a liberação automática desses recursos, pois são gerenciados fora da JVM.</li>
     *   <li>Após chamar {@code dispose()}, nenhum método que dependa desses recursos deve ser utilizado.</li>
     * </ul>
     */
    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        super.dispose();
    }
}
