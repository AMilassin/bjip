package com.halfeleven.glass.jira;


import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;


/**
 *
 * Verify if the user is logged in or not.
 *
 */
public class UserLoggedInCondition extends AbstractWebCondition {

    @Override
    public boolean shouldDisplay(ApplicationUser u, JiraHelper jh) {
        return u != null;
    }

}
