package com.poc.configserver.domain.entity;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "properties")
public class Properties implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PropertiesId id;
    @Column(name = "config_value")
    private String value;

    public Properties(final PropertiesId id, final String value) {
        this.id = id;
        this.value = value;
    }

    public Properties() {
    }

    public PropertiesId getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(PropertiesId id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
