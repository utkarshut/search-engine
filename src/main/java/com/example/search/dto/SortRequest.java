package com.example.search.dto;

public class SortRequest {

    private String field;
    private String order;

    public SortRequest(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() { return field; }
    public String getOrder() { return order; }
}