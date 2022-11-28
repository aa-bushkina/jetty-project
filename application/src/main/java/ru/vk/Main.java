package ru.vk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.Application;
import ru.vk.application.ApplicationModule;

public class Main
{
  public static void main(@NotNull String[] args)
  {
    final Injector injector = Guice.createInjector(new ApplicationModule(args));
    final var applicationInstance = injector.getInstance(Application.class);
    applicationInstance.makeDB("db");
  }
}
