package app.evoMouse;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Classe principal responsável por inicializar a aplicação LibGDX.
 *
 * <p>Esta classe configura o ambiente de execução utilizando o backend LWJGL3,
 * define o título e o tamanho da janela e instancia o jogo principal {@link Isometric}.
 * </p>
 *
 * <p>É o ponto de entrada da aplicação, onde o método {@code main} é executado
 * para iniciar o ciclo de vida do jogo.</p>
 *
 * @author
 * @version 1.0
 * @since 2025
 */
public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // Definir título da janela
        config.setTitle("2D - DemonSlayer");

        // Definir tamanho da janela
        config.setWindowedMode(GameScreen.WIDTH, GameScreen.HEIGHT);

        // Criar aplicação
        new Lwjgl3Application(new Isometric(), config);
    }
}
