package com.halfeleven.jira.he.plugin.resources;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.webresource.api.assembler.PageBuilderService;
import com.atlassian.webresource.api.assembler.RequiredResources;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 */
public class ResourcesServlet extends HttpServlet {


    private PageBuilderService pageBuilderService;

    @Autowired
    public ResourcesServlet(@ComponentImport PageBuilderService pageBuilderService) {
        this.pageBuilderService = pageBuilderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequiredResources resource = pageBuilderService.assembler().resources().requireWebResource("com.halfeleven.bjip.helloplugin1:helloplugin1.resources");

        RequiredResources mainRes = resource.requireWebResource("main.js");

        resp.setStatus(200);
    }


}
