package ru.vk.application;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jetbrains.annotations.NotNull;
import ru.vk.server.MyServer;
import ru.vk.server.ProductServlet;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  final private MyServer myServer;
  final private ProductServlet productServlet;

  @Inject
  public Application(@NotNull final FlywayInitializer initializer,
                     @NotNull final MyServer myServer,
                     @NotNull final ProductServlet productServlet) {
    this.initializer = initializer;
    this.myServer = myServer;
    this.productServlet = productServlet;
  }

  public void makeDB(@NotNull final String path) {
    initializer.initDB(path);
  }

  public void cleanDB() {
    initializer.cleanDB();
  }

  public void startServer() throws Exception {
    final var context = new ServletContextHandler();
    context.setContextPath("/products/");
    context.addServlet(
      new ServletHolder("servlet-products", productServlet), "/"
    );
    final Server server = myServer.build();
    server.setHandler(context);
    server.start();
  }
}
