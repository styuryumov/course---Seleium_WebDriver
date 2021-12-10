package ru.stqa.selenium.test;

import org.junit.Before;
import ru.stqa.selenium.app.Application;

import java.io.IOException;


public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    Application app;

    @Before
    public void start() throws IOException {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.quit();
            app = null;
        }));
    }
}
