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
        filters.add(new FilterRequest("age", ">", "20"));

        SortRequest sort = new SortRequest("age", "desc");

        PageRequest page = new PageRequest(0, 2);

        System.out.println(service.search(filters, sort, page));
    }
}