package com.example.search.engine.filter;

import com.example.search.model.Record;

public class AgeFilter implements Filter {

    @Override
    public boolean apply(Record record, String operator, String value) {

        int recordAge = record.getAge();
        int inputAge = Integer.parseInt(value);

        return switch (operator) {
            case "=" -> recordAge == inputAge;
            case ">" -> recordAge > inputAge;
            case "<" -> recordAge < inputAge;
            default -> false;
        };
    }
}