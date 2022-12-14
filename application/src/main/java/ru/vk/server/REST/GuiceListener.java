package ru.vk.server.REST;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.repository.OrganizationDAO;
import ru.vk.application.repository.ProductDAO;
import ru.vk.application.utils.DBProperties;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {
  @NotNull
  private final DBProperties dbProperties;

  public GuiceListener(@NotNull final DBProperties dbProperties) {
    this.dbProperties = dbProperties;
  }

  @Override
  protected List<? extends Module> getModules(ServletContext context) {
    return Collections.singletonList(new GuiceModule(dbProperties));
  }

  private static final class GuiceModule extends AbstractModule {
    @NotNull
    private final DBProperties dbProperties;

    public GuiceModule(@NotNull final DBProperties dbProperties) {
      this.dbProperties = dbProperties;
    }

    @SuppressWarnings("PointlessBinding")
    @Override
    protected void configure() {
      bind(ProductREST.class).toInstance(new ProductREST(
        new ProductDAO(dbProperties),
        new OrganizationDAO(dbProperties)));
      bind(InfoREST.class);
    }
  }
}