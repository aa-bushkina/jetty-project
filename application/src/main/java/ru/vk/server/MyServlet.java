package ru.vk.server;

import generated.tables.records.OrganizationsRecord;
import generated.tables.records.ProductsRecord;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import ru.vk.application.repository.OrganizationDAO;
import ru.vk.application.repository.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static generated.tables.Organizations.ORGANIZATIONS;

public class MyServlet extends HttpServlet {
  ProductDAO productDAO;
  OrganizationDAO organizationDAO;
  final Map<String, String> data = new ConcurrentHashMap<>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final var id = req.getParameter("id");
    if (id == null) {
      resp.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }
    final var cacheControl = req.getHeader("Cache-control");
    try (final PrintWriter out = resp.getWriter()) {
      out.println("Data id = " + data.get(id) + ", cache-control = " + cacheControl);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
  }
}
