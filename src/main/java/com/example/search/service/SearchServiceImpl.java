package com.example.search.service;

import com.example.search.model.Record;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    private List<Record> database = new ArrayList<>();

    public SearchServiceImpl() {
        database.add(new Record(1, "John", 25));
        database.add(new Record(2, "Alice", 30));
        database.add(new Record(3, "Bob", 25));
    }

    @Override
    public List<Record> search(String field, String value) {

        List<Record> result = new ArrayList<>();

        for (Record record : database) {

            if (field.equals("name") && record.getName().equals(value)) {
                result.add(record);
            }

            if (field.equals("age") && String.valueOf(record.getAge()).equals(value)) {
                result.add(record);
            }
        }

        return result;
    }
}