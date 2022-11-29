package ru.vk.application.repository;

import com.google.inject.Inject;
import generated.tables.records.OrganizationsRecord;
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

import static generated.tables.Organizations.ORGANIZATIONS;
import static org.jooq.impl.DSL.row;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class OrganizationDAO implements Dao<OrganizationsRecord> {
  @NotNull
  final DBProperties dbProperties;

  @Inject
  public OrganizationDAO(@NotNull final DBProperties dbProperties) {
    this.dbProperties = dbProperties;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
      dbProperties.connection() + dbProperties.name(),
      dbProperties.username(),
      dbProperties.password());
  }

  @Override
  public @NotNull OrganizationsRecord get(final int id) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final Record record = context
        .select()
        .from(ORGANIZATIONS)
        .where(ORGANIZATIONS.ID.eq(id))
        .fetchOne();
      return (record == null) ? new OrganizationsRecord() : record.into(ORGANIZATIONS);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with id " + id + "not found");
  }

  @Override
  public @NotNull List<OrganizationsRecord> all() {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final @NotNull Result<Record> result = context
        .select()
        .from(ORGANIZATIONS)
        .fetch();
      ArrayList<OrganizationsRecord> list = new ArrayList<>();
      result.forEach(record -> list.add((OrganizationsRecord) record));
      return list;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public int save(@NotNull final OrganizationsRecord entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context
        .insertInto(ORGANIZATIONS, ORGANIZATIONS.NAME, ORGANIZATIONS.INN)
        .values(entity.getName(), entity.getInn())
        .execute();
      return context.lastID().intValue();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public void update(@NotNull final OrganizationsRecord entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context.update(ORGANIZATIONS)
        .set(row(ORGANIZATIONS.NAME, ORGANIZATIONS.INN),
          row(entity.getName(), entity.getInn()))
        .where(ORGANIZATIONS.ID.eq(entity.getId()))
        .execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(@NotNull final OrganizationsRecord entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context
        .delete(ORGANIZATIONS)
        .where(ORGANIZATIONS.ID.eq(entity.getId()))
        .execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public @NotNull OrganizationsRecord findByName(@NotNull final String name) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final Record record = context
        .select()
        .from(ORGANIZATIONS)
        .where(ORGANIZATIONS.NAME.eq(name))
        .fetchOne();
      return (record == null) ? new OrganizationsRecord() : record.into(ORGANIZATIONS);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with name " + name + "not found");
  }
}
