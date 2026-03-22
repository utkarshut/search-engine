package com.example.search.engine.sort;

import com.example.search.model.Record;
import java.util.Comparator;

public interface SortStrategy {

    Comparator<Record> getComparator(String order);
}