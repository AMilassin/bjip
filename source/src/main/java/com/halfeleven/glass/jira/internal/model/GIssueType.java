package com.halfeleven.glass.jira.internal.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * Issue Type bean.
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GIssueType {

    @XmlElement(name = "id")
    private final String id;

    @XmlElement(name = "name")
    private final String name;

    @XmlElement(name = "description")
    private final String description;

    @XmlElement(name="subTask")
    private final boolean subTask;


    public GIssueType(final String id, final String name, final String description, boolean subTask) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subTask = subTask;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSubTask() {
        return subTask;
    }
}
