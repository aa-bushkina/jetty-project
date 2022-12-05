package ru.vk.server.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import generated.tables.pojos.Organizations;
import generated.tables.pojos.Products;
import org.jetbrains.annotations.NotNull;
import ru.vk.application.repository.OrganizationDAO;
import ru.vk.application.repository.ProductDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public final class ProductREST {
  @NotNull
  final ProductDAO productDAO;
  @NotNull
  final OrganizationDAO organizationDAO;

  @Inject
  public ProductREST(@NotNull final ProductDAO productDAO,
                     @NotNull final OrganizationDAO organizationDAO) {
    this.organizationDAO = organizationDAO;
    this.productDAO = productDAO;
  }

  @Path("/add")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(@QueryParam("name") String name,
                       @QueryParam("organization") String organization,
                       @QueryParam("amount") Integer amount) throws JsonProcessingException {
    if ((name == null) || (organization == null) || (amount == null) ||
      (amount < 0)) {
      return Response.status(Response.Status.BAD_REQUEST)
        .header(HttpHeaders.CACHE_CONTROL, "no-cache")
        .build();
    }

    Integer organizationId = organizationDAO.findByName(organization).getId();
    if (organizationId == null) {
      organizationDAO.save(new Organizations(0, organization));
      organizationId = organizationDAO.findByName(organization).getId();
    }
    final Products product = productDAO.findByNameAndOrganization(name, organizationId);
    final Integer sameRecordId = product.getId();
    if (sameRecordId != null) {
      productDAO.update(new Products(
        sameRecordId,
        name,
        organizationId,
        product.getAmount() + amount));
    } else {
      productDAO.save(new Products(0, name, organizationId, amount));
    }
    @NotNull final ObjectMapper objectMapper = new ObjectMapper();
    return Response.ok(objectMapper.writeValueAsString(productDAO.findByName(name)))
      .header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
  }

  @Path("/all")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response get() throws JsonProcessingException {
    @NotNull final ObjectMapper objectMapper = new ObjectMapper();
    return Response.ok(objectMapper.writeValueAsString(productDAO.all()))
      .header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
  }
}
