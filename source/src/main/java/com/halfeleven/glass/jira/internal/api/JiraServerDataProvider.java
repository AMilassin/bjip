package com.halfeleven.glass.jira.internal.api;

import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.jira.issue.fields.config.manager.IssueTypeSchemeManager;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserProfile;
import com.halfeleven.glass.jira.internal.model.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;


/**
 *
 * Internal accessor to JIRA API.
 *
 */
@Component
public class JiraServerDataProvider implements JiraDataProvider {

    @Inject
    @ComponentImport
    private ProjectManager projectManager;

    @Inject
    @ComponentImport
    private UserManager userManager;

    @Inject
    @ComponentImport
    private IssueTypeSchemeManager issueTypeSchemeManager;

    @Inject
    private GObjectConverter objectConverter;


    @Override
    public List<GProject> listProjects() {
        List<Project> jiraProjects = projectManager.getProjectObjects();
        List<GProject> glassProjects = objectConverter.convertProjects(jiraProjects);
        return glassProjects;
    }

    @Override
    public GProject fetchProject(String projectKey) {
        Project jiraProject = projectManager.getProjectObjByKey(projectKey);
        GProject glassProject = objectConverter.convertProject(jiraProject);
        return glassProject;
    }

    @Override
    public List<GIssueType> listIssueTypes(String projectKey) {
        Project jiraProject = projectManager.getProjectObjByKey(projectKey);
        Collection<IssueType> jiraIssueTypes = issueTypeSchemeManager.getIssueTypesForProject(jiraProject);
        List<GIssueType> glassIssueTypes = objectConverter.convertIssueTypes(jiraIssueTypes);
        return glassIssueTypes;
    }

    @Override
    public List<GFieldConfigScheme> listIssueTypeAllRelatedSchemes(String issueTypeid) {
        Collection<FieldConfigScheme> jiraAllRelatedSchemes = issueTypeSchemeManager.getAllRelatedSchemes(issueTypeid);
        List<GFieldConfigScheme> glassFieldConfigSchemes = objectConverter.convertSchemes(jiraAllRelatedSchemes);
        return glassFieldConfigSchemes;
    }

    @Override
    public GUser fetchUser(String userName) {
        UserProfile jiraUser = userManager.getUserProfile(userName);
        GUser glassUser = objectConverter.convertUser(jiraUser);
        return glassUser;
    }

}
