package ru.skypro.homework.dto;

import ru.skypro.homework.model.Comment;

import java.util.List;

public class ResponseWrapperComments {
    private int count;
    private List<Comment> results;

    public ResponseWrapperComments() {
    }

    public ResponseWrapperComments(int count, List<Comment> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }
}