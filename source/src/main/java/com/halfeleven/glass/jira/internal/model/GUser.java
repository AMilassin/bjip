package com.halfeleven.glass.jira.internal.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * User object
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GUser {

    @XmlElement(name = "key")
    private final String userKey;

    @XmlElement(name = "username")
    private final String userName;

    @XmlElement(name = "fullname")
    private final String displayName;

    @XmlElement(name = "email")
    private final String email;



    public GUser(final String userKey, final String userName, final String displayName, final String email) {
        this.userKey = userKey;
        this.userName = userName;
        this.displayName = displayName;
        this.email = email;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserEmail() {
        return email;
    }

}
