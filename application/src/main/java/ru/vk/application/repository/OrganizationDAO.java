package ru.vk.application.repository;

import com.google.inject.Inject;
import generated.tables.pojos.Organizations;
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

import static generated.tables.Organizations.ORGANIZATIONS;
import static org.jooq.impl.DSL.row;

@SuppressWarnings({"NotNullNullableValidation", "SqlNoDataSourceInspection", "SqlResolve"})
public final class OrganizationDAO implements Dao<Organizations> {
  @NotNull
  private final DBProperties dbProperties;

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
  public @NotNull Organizations get(final int id) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final Organizations organization;
      try {
        organization = context
          .select()
          .from(ORGANIZATIONS)
          .where(ORGANIZATIONS.ID.eq(id))
          .fetchOne().into(Organizations.class);
      } catch (NullPointerException exception) {
        return new Organizations(null, null);
      }
      return organization;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with id " + id + "not found");
  }

  @Override
  public @NotNull List<Organizations> all() {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final @NotNull List<Organizations> result = context
        .select()
        .from(ORGANIZATIONS)
        .fetch().into(Organizations.class);
      return new ArrayList<>(result);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public int save(@NotNull final Organizations entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context
        .insertInto(ORGANIZATIONS, ORGANIZATIONS.NAME)
        .values(entity.getName())
        .execute();
      return context.lastID().intValue();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public void update(@NotNull final Organizations entity) {
    try (var conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      context.update(ORGANIZATIONS)
        .set(row(ORGANIZATIONS.NAME),
          row(entity.getName()))
        .where(ORGANIZATIONS.ID.eq(entity.getId()))
        .execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(@NotNull final Organizations entity) {
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

  public @NotNull Organizations findByName(@NotNull final String name) {
    try (Connection conn = getConnection()) {
      final DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);
      final List<Organizations> organizations = context
        .select()
        .from(ORGANIZATIONS)
        .where(ORGANIZATIONS.NAME.eq(name))
        .fetch().into(Organizations.class);
      return (organizations.isEmpty())
        ? new Organizations(null, null)
        : organizations.get(0);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    throw new IllegalStateException("Record with name " + name + "not found");
  }
}
