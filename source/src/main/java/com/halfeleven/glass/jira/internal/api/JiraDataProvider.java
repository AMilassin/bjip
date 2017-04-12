package com.halfeleven.glass.jira.internal.api;

import com.halfeleven.glass.jira.internal.model.GFieldConfigScheme;
import com.halfeleven.glass.jira.internal.model.GIssueType;
import com.halfeleven.glass.jira.internal.model.GProject;
import com.halfeleven.glass.jira.internal.model.GUser;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Data collection interface.
 */
@Component
public interface JiraDataProvider {

    List<GProject> listProjects();

    GProject fetchProject(String projectKey);

    GUser fetchUser(String userKey);

    List<GIssueType> listIssueTypes(String projectKey);

    List<GFieldConfigScheme> listIssueTypeAllRelatedSchemes(String issueTypeid);

}
