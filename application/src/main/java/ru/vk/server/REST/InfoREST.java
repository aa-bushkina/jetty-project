package ru.vk.server.REST;

import org.apache.commons.io.FileUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@SuppressWarnings("NotNullNullableValidation")
@Path("/info")
public final class InfoREST {
  @GET
  @Produces({"text/html"})
  public String get() throws IOException, URISyntaxException {
    return content();
  }

  private String content() throws IOException, URISyntaxException {
    return FileUtils.readFileToString(
      new File(getClass().getClassLoader().getResource("info/helpInfo.html").toURI()),
      "utf-8");
  }
}