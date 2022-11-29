package ru.vk.server;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyServlet extends HttpServlet {
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
    final var key = req.getParameter("key");
    final var value = req.getParameter("value");

    if ((key == null) || (value == null)) {
      resp.setStatus(HttpStatus.BAD_REQUEST_400);
      return;
    }

    data.put(key, value);
    resp.setStatus(HttpServletResponse.SC_OK);
  }
}
