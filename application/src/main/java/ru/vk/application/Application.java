package ru.vk.application;

import com.google.inject.Inject;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.QoSFilter;
import org.eclipse.jetty.util.resource.Resource;
import org.jetbrains.annotations.NotNull;
import ru.vk.Main;
import ru.vk.server.MyServer;
import ru.vk.server.filters.OnlyGetFilter;
import ru.vk.server.security.SecurityHandlerBuilder;
import ru.vk.server.servlets.ProductServlet;

import java.net.URL;
import java.util.EnumSet;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  @NotNull
  final private MyServer myServer;
  @NotNull
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
    final QoSFilter onlyOneRequestFilter = new QoSFilter();
    final FilterHolder filterOneHolder = new FilterHolder(onlyOneRequestFilter);

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

    filterOneHolder.setInitParameter("maxRequests", "1");
    context.addFilter(filterOneHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

    final String hashConfig = Main.class.getResource("/config/hash_config").toExternalForm();
    final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
    final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
    server.addBean(hashLoginService);
    securityHandler.setHandler(context);
    server.setHandler(securityHandler);

    server.start();
  }
}
