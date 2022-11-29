package ru.vk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.Application;
import ru.vk.application.ApplicationModule;

public class Main {
  public static void main(@NotNull String[] args) throws Exception {
    final Injector injector = Guice.createInjector(new ApplicationModule(args));
    final var app = injector.getInstance(Application.class);
    app.makeDB("db");
    app.startServer();
    ;
  }
}
