package org.teamfarce.mirch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.teamfarce.mirch.MIRCH;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "MIRCH";
        config.width = 1366;
        config.height = 768;
        new LwjglApplication(new MIRCH(), config);
    }
}
