package com.eazybytes.eazyschool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Holiday extends BaseEntity{
    //modified for Displaying Holidays from DB
    // rowmapper changes
//    private final String day;
    private  String day;
    private  String reason;
    private  Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }

    /*public Holiday(String day, String reason, Type type) {
        this.day = day;
        this.reason = reason;
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public Type getType() {
        return type;
    }*/
}
