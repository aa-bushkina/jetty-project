package ru.vk.application.repository;

import com.google.inject.Inject;
import generated.tables.records.ProductsRecord;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
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
public final class ProductDAO implements Dao<ProductsRecord> {
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
  public @NotNull ProductsRecord get(final int id) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final Record record = context
        .select()
        .from(PRODUCTS)
        .where(PRODUCTS.ID.eq(id))
        .fetchOne();
      return (record == null) ? new ProductsRecord() : record.into(PRODUCTS);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with id " + id + "not found");
  }

  @Override
  public @NotNull List<ProductsRecord> all() {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final @NotNull Result<Record> result = context
        .select()
        .from(PRODUCTS)
        .fetch();
      ArrayList<ProductsRecord> list = new ArrayList<>();
      result.forEach(record -> list.add((ProductsRecord) record));
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public int save(@NotNull final ProductsRecord entity) {
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
  public void update(@NotNull final ProductsRecord entity) {
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
  public void delete(@NotNull final ProductsRecord entity) {
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


}
