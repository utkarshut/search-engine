package com.example.search.service;

import com.example.search.dto.FilterRequest;
import com.example.search.dto.PageRequest;
import com.example.search.dto.SortRequest;
import com.example.search.engine.filter.Filter;
import com.example.search.engine.sort.SortStrategy;
import com.example.search.model.Record;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchServiceImpl implements SearchService {

    private List<Record> database = new ArrayList<>();
    private Map<String, Filter> filters;
    private Map<String, SortStrategy> sorters;
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public SearchServiceImpl(Map<String, Filter> filters,
                             Map<String, SortStrategy> sorters) {
        this.filters = filters;
        this.sorters = sorters;
        database.add(new Record(1, "John", 25));
        database.add(new Record(2, "Alice", 30));
        database.add(new Record(3, "Bob", 25));
    }

    @Override
    public List<Record> search(List<FilterRequest> searchFilters, SortRequest sortRequest,
                               PageRequest pageRequest) {

        List<Future<List<Record>>> futures = new ArrayList<>();

        int chunkSize = Math.max(1, database.size() / 4);

        for (int i = 0; i < database.size(); i += chunkSize) {

            int start = i;
            int end = Math.min(i + chunkSize, database.size());

            List<Record> subList = database.subList(start, end);

            Callable<List<Record>> task = () -> {

                List<Record> partialResult = new ArrayList<>();

                for (Record record : subList) {

                    boolean matches = true;

                    if (searchFilters != null) {
                        for (FilterRequest request : searchFilters) {

                            Filter filter = filters.get(request.getField());

                            if (filter == null ||
                                    !filter.apply(record, request.getOperator(), request.getValue())) {

                                matches = false;
                                break;
                            }
                        }
                    }

                    if (matches) {
                        partialResult.add(record);
                    }
                }

                return partialResult;
            };

            futures.add(executor.submit(task));
        }
        List<Record> result = new ArrayList<>();

        for (Future<List<Record>> future : futures) {
            try {
                result.addAll(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
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

            result = new ArrayList<>(result.subList(start, end));
        }
        shutdown();
        return result;
    }
    public void shutdown() {
        executor.shutdown();
    }
}