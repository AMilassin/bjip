package com.halfeleven.jira.he.plugin.web;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.halfeleven.jira.he.plugin.model.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static webwork.action.Action.INPUT;


/**
 * Created by mila on 2017. 01. 06..
 */
public class JiraHEWebAction extends JiraWebActionSupport {

    @Override
    public String doDefault() {
        return INPUT;
    }


    @Override
    public String doExecute() {
        return INPUT;
    }


    @GET
    @Path("/list")
    public Response getUserPreference() {
        return null;
    }
}