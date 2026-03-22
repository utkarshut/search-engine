package com.example.search.main;

import com.example.search.engine.filter.AgeFilter;
import com.example.search.engine.filter.Filter;
import com.example.search.engine.filter.NameFilter;
import com.example.search.engine.sort.AgeSort;
import com.example.search.engine.sort.NameSort;
import com.example.search.engine.sort.SortStrategy;
import com.example.search.service.SearchService;
import com.example.search.service.SearchServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {

        Map<String, Filter> filters = new HashMap<>();
        filters.put("name", new NameFilter());
        filters.put("age", new AgeFilter());

        Map<String, SortStrategy> sorters = new HashMap<>();
        sorters.put("age", new AgeSort());
        sorters.put("name", new NameSort());

        SearchService service = new SearchServiceImpl(filters, sorters);

        MainApp app = new MainApp(service);
        app.run();
    }
}