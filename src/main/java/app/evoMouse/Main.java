package app.evoMouse;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // Definir título da janela (opcional)
        config.setTitle("2D - EvoMouse");

        // Definir tamanho da janela
        config.setWindowedMode(GameScreen.WIDTH, GameScreen.HEIGHT);

        // Criar aplicação
        new Lwjgl3Application(new Isometric(), config);
    }
}
