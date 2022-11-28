package ru.vk.application;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class Application {
  @NotNull
  final private FlywayInitializer initializer;

  @Inject
  public Application(@NotNull FlywayInitializer initializer) {
    this.initializer = initializer;
  }

  public void makeDB(@NotNull final String path) {
    initializer.initDB(path);
  }

  public void cleanDB() {
    initializer.cleanDB();
  }
}
