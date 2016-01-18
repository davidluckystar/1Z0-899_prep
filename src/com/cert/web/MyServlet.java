package com.cert.web;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * User: david
 * Date: 1/14/2016
 * Time: 12:02 AM
 */
@WebServlet(name = "MyServlet", urlPatterns = "/ms")
public class MyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println(attributeNames.nextElement());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        printTraceData(request);
        response.setHeader("hello", "world");
//        request.getRequestDispatcher("/include?alala=ololo").include(request, response);
        request.getServletContext().getRequestDispatcher("/include?alala=ololo").include(request, response);
//        request.getServletContext().getNamedDispatcher("/include?alala=ololo").include(request, response);
        System.out.println("Hello header: " + response.getHeader("hello"));
    }

    private void printTraceData(HttpServletRequest request) {
        // params
        System.out.println("Params");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String param : parameterMap.keySet()) {
            System.out.println(param);
            for (String value : parameterMap.get(param)) {
                System.out.println("# " + value);
            }

        }

        // headers
        System.out.println("----------------");
        System.out.println("Headers");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hname = headerNames.nextElement();
            System.out.println(hname + ": " + request.getHeader(hname));
        }

        // attrs
        System.out.println("----------------");
        System.out.println("Attributes");
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println(attributeNames.nextElement());
        }

        // paths
        System.out.println("----------------");
        System.out.println("Context-path: " + request.getContextPath());
        System.out.println("Servlet-path: " + request.getServletPath());
        System.out.println("PathInfo: " + request.getPathInfo());
//        System.out.println("real path: " + request.getRealPath("web.xml"));
        System.out.println("translated path: " + request.getPathTranslated());

        // cookies
        System.out.println("----------------");
        System.out.println("Cookies");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " " + cookie.getValue() + " " + cookie.getPath());
            }
        }

        // ssl
        System.out.println("-----------------");
        System.out.println("SSL");
        System.out.println("javax.servlet.request.cipher_suite: " + request.getAttribute("javax.servlet.request.cipher_suite"));

        // locale
        System.out.println("--------------------");
        System.out.println("Locale");
        System.out.println(request.getLocale());
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            System.out.println("# " + locales.nextElement());
        }

        // servlet context
        System.out.println("----------------------");
        System.out.println("Servlet Context");
        System.out.println("Servlet registrations");
        ServletContext servletContext = request.getServletContext();
        Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
        for (String name : servletRegistrations.keySet()) {
            ServletRegistration servletRegistration = servletRegistrations.get(name);
            System.out.println("# " + name + " " + servletRegistration.getClassName());
        }
        System.out.println("Filter registration");
        Map<String, ? extends FilterRegistration> filterRegistrations = servletContext.getFilterRegistrations();
        for (String name : filterRegistrations.keySet()) {
            FilterRegistration filterRegistration = filterRegistrations.get(name);
            System.out.println("# " + name + " " + filterRegistration.getClassName());
            for (String s : filterRegistration.getServletNameMappings()) {
                System.out.println("## <servlet-name> " + s);
            }
            for (String s : filterRegistration.getUrlPatternMappings()) {
                System.out.println("## <url-pattern> " + s);
            }
        }
    }
}
