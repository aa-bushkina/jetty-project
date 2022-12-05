package ru.vk.application;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jetbrains.annotations.NotNull;
import ru.vk.server.MyServer;
import ru.vk.server.REST.GuiceListener;
import ru.vk.server.filters.OnlyGetFilter;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  @NotNull
  final private MyServer myServer;

  @Inject
  public Application(@NotNull final FlywayInitializer initializer,
                     @NotNull final MyServer myServer) {
    this.initializer = initializer;
    this.myServer = myServer;
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

    ServletContextHandler context = new ServletContextHandler();
    context.addServlet(HttpServletDispatcher.class, "/");
    context.addEventListener(new GuiceListener());
    server.setHandler(context);
   /* final QoSFilter onlyOneRequestFilter = new QoSFilter();
    final FilterHolder filterOneHolder = new FilterHolder(onlyOneRequestFilter);
    filterOneHolder.setInitParameter("maxRequests", "1");
    context.addFilter(filterOneHolder, "/*", EnumSet.of(DispatcherType.REQUEST));*/

    /*context.addServlet(
      new ServletHolder("servlet-products", productServlet), "/products"
    );*/

   /* final URL resource = Main.class.getResource("/info");
    context.setBaseResource(Resource.newResource(resource.toExternalForm()));
    context.setWelcomeFiles(new String[]{"/helpInfo.html"});
    context.addServlet(
      new ServletHolder("servlet-help", DefaultServlet.class), "/");
    context.addFilter(filterGetHolder, "/", EnumSet.of(DispatcherType.REQUEST));*/

    /*final String hashConfig = Main.class.getResource("/config/hash_config").toExternalForm();
    final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
    final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
    server.addBean(hashLoginService);
    securityHandler.setHandler(context);
    server.setHandler(securityHandler);*/

    server.start();
  }
}
