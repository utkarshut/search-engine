package com.example.search.main;

import com.example.search.service.SearchService;
import com.example.search.service.SearchServiceImpl;

public class Runner {

    public static void main(String[] args) {

        SearchService service = new SearchServiceImpl();

        MainApp app = new MainApp(service);
        app.run();
    }
}