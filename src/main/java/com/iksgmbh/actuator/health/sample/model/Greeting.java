package com.iksgmbh.actuator.health.sample.model;

import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Greeting model.
 */
public class Greeting {

    /** Entity Id */
    private Long id;

    /** Name */
    private String name;


    /**
     * Constructor.
     */
    public Greeting() {
        super();
    }

    /**
     * Constructor.
     *
     * @param id    Entity Id
     * @param name  Name
     */
    public Greeting(final Long id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * Check if greeting data is valid.
     *
     * @return true if greeting data is valid, otherwise false.
     */
    public boolean isValid() {
        return StringUtils.hasText(name);
    }

    @Override
    public String toString() {
        return "Greeting{" +
               "id=" + id +
               ", name='" + name + "'" +
               "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Greeting greeting = (Greeting) o;
        return Objects.equals(id, greeting.id) &&
               Objects.equals(name, greeting.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // Getter + setter

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
