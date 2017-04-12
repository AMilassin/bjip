package com.halfeleven.glass.jira.internal.model;


import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.sal.api.user.UserProfile;
import com.halfeleven.glass.jira.internal.api.JiraDataProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 *
 * Convert between Jira and GLaSS model objects
 *
 */
@Component
public class GObjectConverter {

    @Inject
    private JiraDataProvider dataProvider;


    public List<GProject> convertProjects(Collection<Project> jiraProjects) {
        if (jiraProjects == null || jiraProjects.size() == 0) {
            return Collections.emptyList();
        }

        List<GProject> glassProjects =
                jiraProjects.stream()
                            .map(this::convertProject)
                            .collect(toList());


        return glassProjects;
    }

    public GProject convertProject(final Project project) {
        GUser lead = dataProvider.fetchUser(project.getLeadUserKey());
        GProject gProject = new GProject(project.getName(), lead);
        return gProject;
    }

    public GUser convertUser(UserProfile jiraUser) {
        return new GUser(
                jiraUser.getUserKey().getStringValue(),
                jiraUser.getUsername(),
                jiraUser.getFullName(),
                jiraUser.getEmail()
        );
    }

    public List<GIssueType> convertIssueTypes(Collection<IssueType> jiraIssueTypes) {
        if (jiraIssueTypes == null || jiraIssueTypes.isEmpty()) {
            return Collections.emptyList();
        }

        List<GIssueType> glassIssueTypes =
                jiraIssueTypes.stream().
                                map(this::convertIssueType).
                                collect(toList());

        return glassIssueTypes;
    }

    private GIssueType convertIssueType(IssueType jiraIssueType) {
        return new GIssueType(
                jiraIssueType.getId(),
                jiraIssueType.getName(),
                jiraIssueType.getDescription(),
                jiraIssueType.isSubTask()
        );
    }

    public List<GFieldConfigScheme> convertSchemes(Collection<FieldConfigScheme> jiraAllRelatedSchemes) {
        if (jiraAllRelatedSchemes == null || jiraAllRelatedSchemes.isEmpty()) {
            return Collections.emptyList();
        }

        List<GFieldConfigScheme> glassFieldConfigSchemes =
                jiraAllRelatedSchemes.stream().
                                    map(this::convertScheme).
                                    collect(toList());

        return glassFieldConfigSchemes;
    }

    private GFieldConfigScheme convertScheme(FieldConfigScheme jiraFieldConfigScheme) {
        return new GFieldConfigScheme(
                jiraFieldConfigScheme.getId(),
                jiraFieldConfigScheme.getName(),
                jiraFieldConfigScheme.getDescription()
        );
    }
}
