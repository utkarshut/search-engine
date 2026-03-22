package com.example.search.engine.sort;

import com.example.search.model.Record;
import java.util.Comparator;

public class NameSort implements SortStrategy {

    @Override
    public Comparator<Record> getComparator(String order) {

        if ("desc".equalsIgnoreCase(order)) {
            return Comparator.comparing(Record::getName).reversed();
        }

        return Comparator.comparing(Record::getName);
    }
}