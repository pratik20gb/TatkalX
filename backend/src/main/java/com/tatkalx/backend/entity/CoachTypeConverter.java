package com.tatkalx.backend.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CoachTypeConverter implements AttributeConverter<CoachType, String> {

    @Override
    public String convertToDatabaseColumn(CoachType attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public CoachType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : CoachType.fromCode(dbData);
    }
}