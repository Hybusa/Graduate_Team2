package ru.skypro.homework.dto.comments;

import java.util.List;

public class ResponseWrapperComments {
    private int count;
    private List<ResponseComment> results;

    public ResponseWrapperComments() {
    }

    public ResponseWrapperComments(int count, List<ResponseComment> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResponseComment> getResults() {
        return results;
    }

    public void setResults(List<ResponseComment> results) {
        this.results = results;
    }
}