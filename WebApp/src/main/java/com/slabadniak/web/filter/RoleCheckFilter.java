package com.slabadniak.web.filter;

import com.slabadniak.web.constant.UserType;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * If user authotization rights doesn't specified,
 * set rights and redirect him on the main page.
 * @author Slabadniak Sergei
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/Controller", "/UploadServlet" }, servletNames = { "Controller", "UploadServlet" })
public class RoleCheckFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        UserType type = (UserType) session.getAttribute("userStatus");
        if (type == null) {
            type = UserType.GUEST;
            session.setAttribute("userStatus", type);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
