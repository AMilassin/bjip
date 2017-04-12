package com.halfeleven.jira.webwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;

public class HEModuleAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(HEModuleAction.class);

    @Override
    public String execute() throws Exception {
        return "success";
    }
}
