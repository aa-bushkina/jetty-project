package ru.vk.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.*;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"NotNullNullableValidation", "FieldCanBeLocal"})
public final class MyServer {
  @NotNull
  private final Server server;
  private static final int port = 3466;

  @Inject
  public MyServer(@NotNull final Server server) {
    this.server = server;
  }

  public Server build() {
    return build(port);
  }

  public Server build(int port) {
    final ServerConnector serverConnector = new ServerConnector(
      server,
      new HttpConnectionFactory(new HttpConfiguration()));
    serverConnector.setHost("localhost");
    serverConnector.setPort(port);
    server.setConnectors(new Connector[]{serverConnector});
    return server;
  }
}
