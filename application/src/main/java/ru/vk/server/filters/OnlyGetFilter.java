package ru.vk.server.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("NotNullNullableValidation")
public final class OnlyGetFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (httpServletRequest.getMethod().equals("GET")) {
      chain.doFilter(request, response);
    } else {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  @Override
  public void destroy() {
  }
}