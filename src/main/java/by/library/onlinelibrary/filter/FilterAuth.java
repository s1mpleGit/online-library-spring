//package by.library.onlinelibrary.filter;
//
//import by.library.onlinelibrary.model.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/")
//public class FilterAuth implements Filter {
//
//    private ServletContext sc;
//
//    public void init(FilterConfig config) throws ServletException {
//        this.sc = config.getServletContext();
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        HttpSession session = request.getSession(false);
//        String uri = request.getRequestURI();
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null && !(uri.endsWith("index") || uri.endsWith("registration") || uri.endsWith("book/all"))) {
//            response.sendRedirect("/index");
//        }
//        else if (user != null && user.getStatus().toString().contains("BLOCK") && !uri.endsWith("block")) {
//            response.sendRedirect("/block");
//        }
//        else if (user != null && uri.endsWith("index")) {
//            response.sendRedirect("/book/all");
//        }
//        else if (user != null && user.getStatus().toString().contains("ACTIVE") && user.getRole().toString().contains("USER") && (uri.endsWith("admin") || uri.endsWith("block"))) {
//            response.sendRedirect("/book/all");
//        }
//        else chain.doFilter(request, response);
//    }
//
//    public void destroy() {
//    }
//
//}
