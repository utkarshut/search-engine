package com.example.search.main;

import com.example.search.service.SearchService;

public class MainApp {

    private SearchService service;

    public MainApp(SearchService service) {
        this.service = service;
    }

    public void run() {
        System.out.println(service.search("name", "John"));
        System.out.println(service.search("age", "25"));
    }
}