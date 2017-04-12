package com.halfeleven.jira.he.plugin;

import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

/**
 *
 * Enable display of plug-in.
 *
 */
public class PluginCondition extends AbstractWebCondition {

    @Override
    public boolean shouldDisplay(ApplicationUser applicationUser, JiraHelper jiraHelper) {
        return true;
    }
}
