package com.cert.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: david
 * Date: 1/16/2016
 * Time: 10:12 PM
 */
@WebServlet(name = "ToIncludeServlet", urlPatterns = "/include")
public class ToIncludeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("hello", "not accessible in caller");
        System.out.println("%%%%%%%%% INCLUDE SERBLET %%%%%%%%%%%");
        String[] params = {
                "javax.servlet.include.request_uri",
                "javax.servlet.include.context_path",
                "javax.servlet.include.servlet_path",
                "javax.servlet.include.path_info",
                "javax.servlet.include.query_string"
        };
        for (String param : params) {
            System.out.println(request.getAttribute(param));
        }
        System.out.println("# param1: " + request.getParameter("param1"));

        response.getWriter().print("included string");
    }
}
