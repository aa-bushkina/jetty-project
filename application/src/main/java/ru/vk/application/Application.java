package ru.vk.application;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jetbrains.annotations.NotNull;
import ru.vk.server.MyServer;
import ru.vk.server.MyServlet;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  final private MyServer myServer;
  final private MyServlet myServlet;

  @Inject
  public Application(@NotNull final FlywayInitializer initializer,
                     @NotNull final MyServer myServer,
                     @NotNull final MyServlet myServlet) {
    this.initializer = initializer;
    this.myServer = myServer;
    this.myServlet = myServlet;
  }

  public void makeDB(@NotNull final String path) {
    initializer.initDB(path);
  }

  public void cleanDB() {
    initializer.cleanDB();
  }

  public void startServer() throws Exception {
    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(
      new ServletHolder("servlet-products", myServlet), "/"
    );
    final Server server = myServer.build();
    server.setHandler(context);
    server.start();
  }
}
