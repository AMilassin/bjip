package com.halfeleven.glass.jira;

import com.atlassian.core.util.ClassLoaderUtils;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.halfeleven.glass.jira.internal.configuration.Configuration;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * Main web route servlet.
 *
 * Serves the template file and the static resources.
 *
 * REST APIs are rooted to '<context>/plugins/servlet/...' on JIRA.
 *
 */
@Scanned
public class ProjectWebServlet extends HttpServlet {

    private static final String REST_API_PATH = "/rest/glass/1.0";

    private static final String TEMPLATE_PATH = "templates/main/main.vm";

    private static final String APP_MANIFEST_PATH = "app/asset-manifest.json";


    @ComponentImport
    private final TemplateRenderer templateRenderer;

    @ComponentImport
    private final LoginUriProvider loginUriProvider;

    @ComponentImport
    private final UserManager userManager;

    private final Configuration appConfig;

    private Map<String, String> assets = new HashMap<>();



    @Inject
    public ProjectWebServlet(TemplateRenderer templateRenderer, UserManager userManager, LoginUriProvider loginUriProvider,
                             Configuration appConfig) {
        this.templateRenderer = templateRenderer;
        this.userManager = userManager;
        this.loginUriProvider = loginUriProvider;
        this.appConfig = appConfig;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        // read static asset list
        try {
            InputStream input = ClassLoaderUtils.getResourceAsStream("app/asset-manifest.json", getClass());
            this.assets = new ObjectMapper().readValue(input, HashMap.class);
        } catch (IOException e) {
            // TODO log error
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        if (!checkUserLoggedIn()) {
            redirectToLogin(req, resp);
            return;
        }

        serveTemplateAndResources(req, resp);
    }
    /**
     * Serve the GLaSS main template and the required resources (JS, CSS, etc. files)
     */
    private void serveTemplateAndResources(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        setDefaults(resp);

        // simple route matching
        String path = req.getPathInfo();
        if (path.matches("/static/.*")) {

        } else {
            // by default every non-resource URL gets the template
            // the resources are included in the template
            // the actual URLs are handled by the client side application (e.g.: React)

            // Render Velocity Template
            // The context entries can be used inside the template as variables
            Map<String, Object> context = new HashMap<>();
            context.put("development", appConfig.isDevelopment());
            context.put("rooturl", getRootUrl(req));
            context.put("rootcontexturl", getRootContextUrl(req));
            context.put("rootresturl", getRootRestUrl(req));
            renderHTMLTemplate(resp, context);
        }
    }

    private void renderHTMLTemplate(final HttpServletResponse resp, Map<String, Object> context) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        templateRenderer.render(TEMPLATE_PATH, context, resp.getWriter());
    }

    private void redirectToLogin(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.sendRedirect(loginUriProvider.getLoginUri(getUri(request)).toASCIIString());
    }

    private boolean checkUserLoggedIn() {
        UserProfile user = userManager.getRemoteUser();
        if (user == null) {
            return false;
        }
        return true;
    }

    public void setDefaults(final HttpServletResponse resp) {
        if (appConfig.isDevelopment()) {
            resp.setHeader("Cache-Control", "no-cache");
            resp.setHeader("Pragma", "no-cache");
        }
    }

    private String getRootUrl(final HttpServletRequest req) {
        String serverName = req.getServerName();
        int portNumber = req.getServerPort();
        String contextPath = req.getContextPath();
        return "//" + serverName + ":" + portNumber + contextPath + req.getServletPath();
    }

    private String getRootContextUrl(final HttpServletRequest req) {
        return req.getContextPath() + req.getServletPath();
    }

    private String getRootRestUrl(HttpServletRequest req) {
        return req.getContextPath() + REST_API_PATH;
    }

    private URI getUri(final HttpServletRequest request) {
        StringBuffer builder = request.getRequestURL();
        if (request.getQueryString() != null) {
            builder.append("?");
            builder.append(request.getQueryString());
        }
        return URI.create(builder.toString());
    }

}
