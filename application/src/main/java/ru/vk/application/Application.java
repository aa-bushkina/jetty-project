package ru.vk.application;

import com.google.inject.Inject;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.jetbrains.annotations.NotNull;
import ru.vk.Main;
import ru.vk.server.MyServer;
import ru.vk.server.OnlyGetFilter;
import ru.vk.server.ProductServlet;

import java.net.URL;
import java.util.EnumSet;

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
    final Server server = myServer.build();
    final OnlyGetFilter onlyGetFilter = new OnlyGetFilter();
    final FilterHolder filterGetHolder = new FilterHolder(onlyGetFilter);

    final var context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(
      new ServletHolder("servlet-products", productServlet), "/products"
    );

    final URL resource = Main.class.getResource("/info");
    context.setBaseResource(Resource.newResource(resource.toExternalForm()));
    context.setWelcomeFiles(new String[]{"/helpInfo.html"});
    context.addServlet(
      new ServletHolder("servlet-help", DefaultServlet.class), "/");
    context.addFilter(filterGetHolder, "/", EnumSet.of(DispatcherType.REQUEST));

    server.setHandler(context);
    server.start();
  }
}
