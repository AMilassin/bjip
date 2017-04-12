package com.halfeleven.glass.jira.internal.model;


import com.atlassian.jira.project.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 *
 * Glass project bean.
 *
 */
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class GProject {


    @XmlElement(name = "name")
    private final String projectName;

    @XmlElement(name = "leadUser")
    private GUser lead;



    public GProject(final String projectName, final GUser lead) {
        this.projectName = projectName;
        this.lead = lead;
    }

    public GProject(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }


}
