package ru.vk.application.repository;

import com.google.inject.Inject;
import generated.tables.pojos.Products;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.vk.application.utils.DBProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static generated.tables.Products.PRODUCTS;
import static org.jooq.impl.DSL.row;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class ProductDAO implements Dao<Products> {
  @NotNull
  final DBProperties dbProperties;

  @Inject
  public ProductDAO(@NotNull final DBProperties dbProperties) {
    this.dbProperties = dbProperties;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
      dbProperties.connection() + dbProperties.name(),
      dbProperties.username(),
      dbProperties.password());
  }

  @Override
  public @NotNull Products get(final int id) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final Products product;
      try {
        product = context
          .select()
          .from(PRODUCTS)
          .where(PRODUCTS.ID.eq(id))
          .fetchOne().into(Products.class);
      } catch (NullPointerException exception) {
        return new Products(null, null, null, null);
      }
      return product;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with id " + id + "not found");
  }

  @Override
  public @NotNull List<Products> all() {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final @NotNull List<Products> result = context
        .select()
        .from(PRODUCTS)
        .fetch().into(Products.class);
      return new ArrayList<>(result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public int save(@NotNull final Products entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context
        .insertInto(PRODUCTS, PRODUCTS.NAME, PRODUCTS.ORGANIZATION_ID, PRODUCTS.AMOUNT)
        .values(entity.getName(), entity.getOrganizationId(), entity.getAmount())
        .execute();
      return context.lastID().intValue();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public void update(@NotNull final Products entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context.update(PRODUCTS)
        .set(row(PRODUCTS.NAME, PRODUCTS.ORGANIZATION_ID, PRODUCTS.AMOUNT),
          row(entity.getName(), entity.getOrganizationId(), entity.getAmount()))
        .where(PRODUCTS.ID.eq(entity.getId()))
        .execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(@NotNull final Products entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context
        .delete(PRODUCTS)
        .where(PRODUCTS.ID.eq(entity.getId()))
        .execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public @NotNull Products findByName(@NotNull final String name) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final List<Products> products = context
        .select()
        .from(PRODUCTS)
        .where(PRODUCTS.NAME.eq(name))
        .fetch().into(Products.class);
      return (products.isEmpty())
        ? new Products(null, null, null, null)
        : products.get(0);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with name " + name + "not found");
  }

  public @NotNull Products findByNameAndOrganization(@NotNull final String name,
                                                     final int id) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final List<Products> products = context
        .select()
        .from(PRODUCTS)
        .where(PRODUCTS.NAME.eq(name)
          .and(PRODUCTS.ORGANIZATION_ID.eq(id)))
        .fetch().into(Products.class);
      return (products.isEmpty())
        ? new Products(null, null, null, null)
        : products.get(0);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with name " + name + "not found");
  }
}
