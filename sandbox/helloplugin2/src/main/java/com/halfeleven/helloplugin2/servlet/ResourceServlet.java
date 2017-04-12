package com.halfeleven.helloplugin2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(ResourceServlet.class);


    private final PageBuilderService pageBuilderService;

    public ResourceServlet(final PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        pageBuilderService.assembler().resources().requireWebResource("com.halfeleven.helloplugin2:helloplugin2-resources");


        resp.setContentType("text/html");
        resp.getWriter().write("<html><body>Hello World</body></html>");
    }

}