package com.example.search.engine.filter;

import com.example.search.model.Record;

public class NameFilter implements Filter {

    @Override
    public boolean apply(Record record, String operator, String value) {

        if ("=".equals(operator)) {
            return record.getName().equals(value);
        }

        return false;
    }
}