package com.example.search.engine.filter;

import com.example.search.model.Record;

public interface Filter {

    boolean apply(Record record, String operator, String value);
}