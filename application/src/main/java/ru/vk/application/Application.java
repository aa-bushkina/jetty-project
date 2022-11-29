package ru.vk.application;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.vk.server.MyServer;
import ru.vk.server.MyServlet;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  final private MyServer server;
  final private MyServlet servlet;

  @Inject
  public Application(@NotNull final FlywayInitializer initializer,
                     @NotNull final MyServer server,
                     @NotNull final MyServlet servlet) {
    this.initializer = initializer;
    this.server = server;
    this.servlet = servlet;
  }

  public void makeDB(@NotNull final String path) {
    initializer.initDB(path);
  }

  public void cleanDB() {
    initializer.cleanDB();
  }

  public void startServer() throws Exception {
    server.start();
  }
}
