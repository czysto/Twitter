package com.sdacademy.twitter.model;

/**
 * Base entity interface for all stored objects in the system
 */
public interface BaseEntity {
    /**
     * Method returns Entity ID
     *
     * @return the ID of the entity
     */
    Long getId();

    /**
     * Method set Entity ID
     *
     * @param id the ID to set for the entity
     */
    void setId(final Long id);
}
