package ru.vk.server;

import com.google.inject.Inject;
import generated.tables.records.OrganizationsRecord;
import generated.tables.records.ProductsRecord;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.repository.OrganizationDAO;
import ru.vk.application.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;

import static generated.tables.Organizations.ORGANIZATIONS;

public class MyServlet extends HttpServlet {
  @NotNull
  final ProductDAO productDAO;
  @NotNull
  final OrganizationDAO organizationDAO;

  @Inject
  public MyServlet(@NotNull final ProductDAO productDAO,
                   @NotNull final OrganizationDAO organizationDAO) {
    this.organizationDAO = organizationDAO;
    this.productDAO = productDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    try (final PrintWriter out = resp.getWriter()) {
      out.println("Hey");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final var name = req.getParameter("name");
    final var organization = req.getParameter("organization");
    final var amount = req.getParameter("amount");

    if ((name == null) || (organization == null) || (amount == null)) {
      resp.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }
    if (Integer.parseInt(amount) < 0) {
      resp.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }
    Integer organizationId = organizationDAO.findByName(organization).get(ORGANIZATIONS.ID);
    if (organizationId == null) {
      organizationDAO.save(new OrganizationsRecord(0, organization));
      organizationId = organizationDAO.findByName(organization).getId();
    }
    productDAO.save(new ProductsRecord(0, name, organizationId, Integer.parseInt(amount)));
    resp.setStatus(HttpServletResponse.SC_OK);
    try (final PrintWriter out = resp.getWriter()) {
      out.println("Add product " + productDAO.findByName(name));
    }
  }
}
