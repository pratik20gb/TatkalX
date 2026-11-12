package com.tatkalx.backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CoachType {
    _1A("1A"),
    _2A("2A"),
    _3A("3A"),
    SL("SL"),
    CC("CC"),
    EC("EC");

    private final String code;

    CoachType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static CoachType fromCode(String code) {
        for (CoachType type : values()) {
            if (type.code.equalsIgnoreCase(code) || type.name().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown coach type: " + code);
    }
}