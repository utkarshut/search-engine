package com.example.search.service;

import com.example.search.dto.FilterRequest;
import com.example.search.engine.filter.AgeFilter;
import com.example.search.engine.filter.Filter;
import com.example.search.engine.filter.NameFilter;
import com.example.search.model.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchServiceImpl implements SearchService {

    private List<Record> database = new ArrayList<>();
    private Map<String, Filter> filters = new HashMap<>();

    public SearchServiceImpl() {
        database.add(new Record(1, "John", 25));
        database.add(new Record(2, "Alice", 30));
        database.add(new Record(3, "Bob", 25));

        filters.put("name", new NameFilter());
        filters.put("age", new AgeFilter());
    }

    @Override
    public List<Record> search(List<FilterRequest> searchFilters) {

        List<Record> result = new ArrayList<>();

        for (Record record : database) {

            boolean matches = true;

            for (FilterRequest request : searchFilters) {

                Filter filter = filters.get(request.getField());

                if (filter == null ||
                        !filter.apply(record, request.getOperator(), request.getValue())) {

                    matches = false;
                    break;
                }
            }

            if (matches) {
                result.add(record);
            }
        }

        return result;
    }
}