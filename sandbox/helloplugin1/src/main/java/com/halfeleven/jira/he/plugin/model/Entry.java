package com.halfeleven.jira.he.plugin.model;

import net.java.ao.Entity;
import net.java.ao.EntityManager;
import net.java.ao.RawEntity;

import java.beans.PropertyChangeListener;

/**
 * Data Entry
 */
public interface Entry extends Entity {

    String getName();
    void setName(String name);

}
