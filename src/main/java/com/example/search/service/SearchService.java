package com.example.search.service;

import com.example.search.model.Record;
import java.util.List;

public interface SearchService {

    List<Record> search(String field, String value);
}