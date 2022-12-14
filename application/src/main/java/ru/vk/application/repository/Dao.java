package ru.vk.application.repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Dao<T> {
  @NotNull T get(int id);

  @NotNull List<@NotNull T> all();

  int save(@NotNull T entity);

  void update(@NotNull T entity);

  void delete(@NotNull T entity);
}
