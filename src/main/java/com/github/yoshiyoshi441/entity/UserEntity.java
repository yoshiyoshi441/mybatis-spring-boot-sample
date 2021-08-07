package com.github.yoshiyoshi441.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.ZonedDateTime;

public class UserEntity {
    private final Long id;
    private final FirstName firstName;
    private final LastName lastName;
    private final AdditionalData additionalData;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public UserEntity(FirstName firstName, LastName lastName, AdditionalData additionalData) {
        this(null, firstName, lastName, additionalData, null, null);
    }

    private UserEntity(
            Long id, FirstName firstName, LastName lastName,
            AdditionalData additionalData, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.additionalData = additionalData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
