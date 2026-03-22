package com.example.search.service;

import com.example.search.dto.FilterRequest;
import com.example.search.dto.PageRequest;
import com.example.search.dto.SortRequest;
import com.example.search.model.Record;
import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Record> search(List<FilterRequest> filters, SortRequest sortRequest,
                        PageRequest pageRequest);
}