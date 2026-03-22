package com.example.search.service;

import com.example.search.dto.FilterRequest;
import com.example.search.dto.PageRequest;
import com.example.search.dto.SortRequest;
import com.example.search.engine.filter.AgeFilter;
import com.example.search.engine.filter.Filter;
import com.example.search.engine.filter.NameFilter;
import com.example.search.engine.sort.AgeSort;
import com.example.search.engine.sort.NameSort;
import com.example.search.engine.sort.SortStrategy;
import com.example.search.model.Record;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchServiceImpl implements SearchService {

    private List<Record> database = new ArrayList<>();
    private Map<String, Filter> filters = new HashMap<>();
    private Map<String, SortStrategy> sorters = new HashMap<>();

    public SearchServiceImpl() {
        database.add(new Record(1, "John", 25));
        database.add(new Record(2, "Alice", 30));
        database.add(new Record(3, "Bob", 25));

        filters.put("name", new NameFilter());
        filters.put("age", new AgeFilter());
        sorters.put("age", new AgeSort());
        sorters.put("name", new NameSort());
    }

    @Override
    public List<Record> search(List<FilterRequest> searchFilters, SortRequest sortRequest,
                               PageRequest pageRequest) {

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
        if (sortRequest != null) {
            SortStrategy sorter = sorters.get(sortRequest.getField());

            if (sorter != null) {
                result.sort(sorter.getComparator(sortRequest.getOrder()));
            }
        }
        if (pageRequest != null) {

            int start = pageRequest.getPage() * pageRequest.getSize();
            int end = Math.min(start + pageRequest.getSize(), result.size());

            if (start >= result.size()) {
                return new ArrayList<>();
            }

            result = result.subList(start, end);
        }
        return result;
    }
}