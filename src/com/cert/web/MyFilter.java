package com.cert.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * User: david
 * Date: 1/16/2016
 * Time: 3:14 PM
 */
@WebFilter(filterName = "MyFilter", urlPatterns = "/ms"
        , dispatcherTypes = DispatcherType.FORWARD
)
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("#########   A NEW REQUEST      ##############");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("####### INIT FILTER ##########");
    }

}
