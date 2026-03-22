package com.example.search.main;

import com.example.search.dto.FilterRequest;
import com.example.search.dto.PageRequest;
import com.example.search.dto.SortRequest;
import com.example.search.service.SearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApp {

    private SearchService service;

    public MainApp(SearchService service) {
        this.service = service;
    }

    public void run() {
        List<FilterRequest> filters = new ArrayList<>();
        filters.add(new FilterRequest("age", ">", "25"));

        SortRequest sort = new SortRequest("age", "desc");

        PageRequest page = new PageRequest(0, 2);
        validate(filters);
        System.out.println(service.search(filters, sort, page));
    }
    private void validate(List<FilterRequest> filters) {

        if (filters == null || filters.isEmpty()) {
            throw new IllegalArgumentException("Filters cannot be empty");
        }

        for (FilterRequest f : filters) {

            if (f.getField() == null || f.getField().isEmpty()) {
                throw new IllegalArgumentException("Field cannot be null");
            }

            if (f.getOperator() == null) {
                throw new IllegalArgumentException("Operator required");
            }

            if (f.getValue() == null) {
                throw new IllegalArgumentException("Value required");
            }
        }
    }
}