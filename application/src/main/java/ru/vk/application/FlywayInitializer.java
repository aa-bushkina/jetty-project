package ru.vk.application;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.utils.DBProperties;

public class FlywayInitializer {
  @NotNull
  private final DBProperties DBProperties;
  private Flyway flyway;

  public FlywayInitializer(@NotNull final DBProperties dbProperties) {
    DBProperties = dbProperties;
  }

  public void initDB(@NotNull final String path) {
    flyway = Flyway
      .configure()
      .dataSource(DBProperties.getConnection() + DBProperties.getName(),
        DBProperties.getUsername(),
        DBProperties.getPassword())
      .cleanDisabled(false)
      .locations(path)
      .load();
    flyway.migrate();
  }

  public void cleanDB() {
    flyway.clean();
  }

}
