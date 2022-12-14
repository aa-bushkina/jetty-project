package ru.vk.application;

import com.google.inject.Inject;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.QoSFilter;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jetbrains.annotations.NotNull;
import ru.vk.Main;
import ru.vk.application.utils.DBProperties;
import ru.vk.server.MyServer;
import ru.vk.server.REST.GuiceListener;
import ru.vk.server.filters.OnlyGetFilter;
import ru.vk.server.security.SecurityHandlerBuilder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;
  @NotNull
  final private MyServer myServer;
  @NotNull
  final private DBProperties dbProperties;

  @Inject
  public Application(@NotNull final FlywayInitializer initializer,
                     @NotNull final MyServer myServer,
                     @NotNull final DBProperties dbProperties) {
    this.initializer = initializer;
    this.myServer = myServer;
    this.dbProperties = dbProperties;
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
    final QoSFilter onlyOneRequestFilter = new QoSFilter();
    final FilterHolder filterOneHolder = new FilterHolder(onlyOneRequestFilter);

    context.addServlet(HttpServletDispatcher.class, "/");
    context.addEventListener(new GuiceListener(dbProperties));

    filterOneHolder.setInitParameter("maxRequests", "1");
    context.addFilter(filterOneHolder, "/*", EnumSet.of(DispatcherType.REQUEST));
    context.addFilter(filterGetHolder, "/info", EnumSet.of(DispatcherType.REQUEST));

    final String hashConfig = Main.class.getResource("/config/hash_config").toExternalForm();
    final HashLoginService hashLoginService = new HashLoginService("login", hashConfig);
    final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(hashLoginService);
    server.addBean(hashLoginService);
    securityHandler.setHandler(context);
    server.setHandler(securityHandler);

    server.start();
  }
}
