package com.jumia.jpay.audit.dto;

public class QueryFieldDto {
    protected Object value;
    protected String type;
    protected String property;
    protected String from;
    protected String to;

    public QueryFieldDto(Object value, String type, String property, String from, String to) {
        this.value = value;
        this.type = type;
        this.property = property;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
