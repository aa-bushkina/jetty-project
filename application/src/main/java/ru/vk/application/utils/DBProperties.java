package ru.vk.application.utils;

import com.google.inject.Inject;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public final class DBProperties {
  private final @NotNull String connection;
  private final @NotNull String name;
  private final @NotNull String username;
  private final @NotNull String password;

  @Inject
  public DBProperties(@NotNull String connection,
                      @NotNull String name,
                      @NotNull String username,
                      @NotNull String password) {
    this.connection = connection;
    this.name = name;
    this.username = username;
    this.password = password;
  }

/*  @Inject
  public DBProperties(DBProperties properties) {
    this.connection = properties.connection;
    this.name = properties.name;
    this.username = properties.username;
    this.password = properties.password;
  }*/

}