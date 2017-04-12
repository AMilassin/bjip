package com.halfeleven.glass.jira.internal.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tibor Szucs on 2017.03.01..
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GFieldConfigScheme {

    @XmlElement(name = "id")
    private final Long id;

    @XmlElement(name = "name")
    private final String name;

    @XmlElement(name = "description")
    private final String description;

    public GFieldConfigScheme(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
