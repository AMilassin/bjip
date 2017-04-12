package com.halfeleven.glass.jira;


import com.halfeleven.glass.jira.internal.api.JiraDataProvider;
import com.halfeleven.glass.jira.internal.model.GFieldConfigScheme;
import com.halfeleven.glass.jira.internal.model.GIssueType;
import com.halfeleven.glass.jira.internal.model.GProject;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;


/**
 *
 * API interface to serve the client side components.
 *
 * REST APIs are rooted to '' on JIRA.
 *
 *
 */
@Path("/")
@Produces("application/json")
public class ClientAPI {

    private final JiraDataProvider dataProvider;

    @Inject
    public ClientAPI(JiraDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }


    @GET
    @Path("/project")
    public Collection<GProject> listProjects() {
        return dataProvider.listProjects();
    }

    @GET
    @Path("/project/{projectKey}")
    public Response getProject(@PathParam("projectKey") final String projectKey) {
        GProject project = dataProvider.fetchProject(projectKey);
        return Response.ok(project).build();
    }

    @GET
    @Path("/project/{projectKey}/issuetypes")
    public Response listProjectIssueTypes(@PathParam("projectKey") final String projectKey) {
        List<GIssueType> glassIssueTypes = dataProvider.listIssueTypes(projectKey);
        return Response.ok(glassIssueTypes).build();
    }

    @GET
    @Path("/project/{projectKey}/issuetypes/{issuetypeid}/issuetypeschemes")
    public Response listIssueTypeSchemes(@PathParam("projectKey") final String projectKey, @PathParam("issuetypeid") final String issueTypeid) {
        List<GFieldConfigScheme> glassFieldConfigSchemes = dataProvider.listIssueTypeAllRelatedSchemes(issueTypeid);
        return Response.ok(glassFieldConfigSchemes).build();
    }

    @GET
    @Path("/project/{projectKey}/users")
    public Object listProjectUsers(@PathParam("projectKey") final String projectKey) {
        return null;
    }

}
