package ru.vk.server;

import com.google.inject.AbstractModule;
import org.eclipse.jetty.server.Server;

public class ServerModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Server.class).toInstance(new Server());
    bind(MyServer.class).toInstance(new MyServer(new Server()));
  }
}