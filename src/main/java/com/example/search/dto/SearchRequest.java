package com.example.search.dto;

public class SearchRequest {

    private String field;
    private String value;

    public SearchRequest(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}