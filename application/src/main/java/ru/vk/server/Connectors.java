package ru.vk.server;


import org.eclipse.jetty.server.*;

@SuppressWarnings("NotNullNullableValidation")
public final class Connectors {
  private static final String hostName = "localhost";
  private static final int hostNum = 3466;

  public static void main(String[] args) throws Exception {
    final Server server = new Server();

    final ServerConnector serverConnector = new ServerConnector(
      server,
      new HttpConnectionFactory(new HttpConfiguration()));

    serverConnector.setHost(hostName);
    serverConnector.setPort(hostNum);

    server.setConnectors(new Connector[]{serverConnector});

    server.start();
  }
}
