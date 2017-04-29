package bo.gaceta.rcb.presentacion;

import bo.gaceta.rcb.modelo.tbUSLogin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltroLogin implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain)
        throws IOException, ServletException {

    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;
    HttpSession session = httpReq.getSession();
    httpResp.setHeader("Cache-Control", "no-cache");
    String paginaSolicitada = httpReq.getRequestURI();
    String contextPath = httpReq.getContextPath();
    tbUSLogin usuario = (tbUSLogin) httpReq.getSession().getAttribute(UtilsPresentacion.VARIABLE_SESION_USUARIO);
    if (usuario != null) {
      httpResp.sendRedirect(contextPath + "/autorizado/principal.xhtml");
      httpReq.getRequestDispatcher("/principal?faces-redirect=true").forward(request, response);
      return;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) {
  }


}
