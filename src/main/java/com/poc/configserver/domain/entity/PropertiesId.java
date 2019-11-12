package com.poc.configserver.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PropertiesId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "config_application")
    private String application;
    @Column(name = "config_profile")
    private String profile;
    @Column(name = "config_label")
    private String label;
    @Column(name = "config_key")
    private String key;

    public PropertiesId() {
    }

    public PropertiesId(final String application, final String profile, final String label, final String key) {
        this.application = application;
        this.profile = profile;
        this.label = label;
        this.key = key;
    }

    public String getApplication() {
        return application;
    }

    public String getProfile() {
        return profile;
    }

    public String getLabel() {
        return label;
    }

    public String getKey() {
        return key;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PropertiesId{" +
                "application='" + application + '\'' +
                ", profile='" + profile + '\'' +
                ", label='" + label + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
